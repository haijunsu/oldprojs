//////////////////////////////////////////
// 用于Elearning课件显示，修改于2002.3.5
//          苏海军
//////////////////////////////////////////

/**
 * CSTreeBean.java
 * Version 0.3, September 12, 2000
 *
 * Copyright (C) 2000 CoolServlets.com. All rights reserved.
 *
 * Derived from CoolServlets tree classes. Maintainer: Kevin Sangeelee
 *
 * ===================================================================
 * The Apache Software License, Version 1.1
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        CoolServlets.com (http://www.coolservlets.com)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Jive" and "CoolServlets.com" must not be used to
 *    endorse or promote products derived from this software without
 *    prior written permission. For written permission, please
 *    contact webmaster@coolservlets.com.
 *
 * 5. Products derived from this software may not be called "Jive",
 *    nor may "Jive" appear in their name, without prior written
 *    permission of CoolServlets.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL COOLSERVLETS.COM OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of CoolServlets.com. For more information
 * on CoolServlets.com, please see <http://www.coolservlets.com>.
 */
package com.htyz.treeBeans;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import com.htyz.*;

/**
 * A CSTreeBean is a server-side JSP JavaBean that generates trees populated from a JDBC
 * data source, and renders the trees as HTML. State information is maintained using URI
 * parameters.
 */

public class CSTreeBean {

	private static Hashtable trees = new Hashtable();

	private Hashtable openNodes = new Hashtable();	// holds an easy-lookup of open nodes while rendering HTML

	private String treeType;						//树类型，code：显示代码树，只有两级
													//        separate：由单一字段按规律分级显示
													//                  根据提供的TREE层次及间隔值来决定分级
													//        coloum：根据提供的列数来显示树，原创

	private int treeDepth;							//separate树的深度
	private int interval;							//separate树的间隔值
	private	String treeIndex;						//separate的主字段， code树的code_id字段
	private	String treeSubIndex;					//code树的code_value字段
	private	String treeRoot;					    //code树的定义节点的值，即如果code_id为这个值，表示这是一个节点
		
	private String treeTitle;						// 树的标题
	private String treeName;						// unique name of this tree
	private String tableName;						// name of the (denormalised) table or view
	private String labelCols;						// comma separated list of columns that hold labels
	private String urlCols;							// comma separated list of corresponding urls
	private String whereClause = "";

	private String sql;								//查询TREE的SQL语句

	private String treePage;						// Which page to send tree URL links to
	private String leafPage;						// Which page to send tree leaf links to
	private String leafTarget;						// Which frame to send non-tree URL links to

	private String openImage="fldo.gif";			// image for open nodes
	private String closedImage="fldc.gif";			// image for closed nodes
	private String leafImage="rgn.gif";				// image for leaf nodes

	private String treeStyle;						// Text style of the table

	private int open = 0;			// Passed by URL to this bean stating which node to open (or close)
	private int absopen;			// what - no abs()??
	private String oldopen = "";	// Automatically maintained URL parameter listing open nodes
	private boolean reload = false;


	// Now on to the real stuff...

	/**
	 * Populates the tree specified in the bean parameter 'treeName', or the default tree
	 * if none was specified. The trees are held statically in a Hashtable, keyed on the tree
	 * name.
	 *
	 * A tree is populated from a denormalised table or a view using JDBC. Ordinarily, this operation
	 * happens only once when the tree is first accessed.
	 *
	 * This method should probably be reorganised to lend itself more to populating from sources other
	 * than JDBC.
	 */

//	public synchronized boolean populateJdbc() {
	public boolean populateJdbc() {

		boolean sqlerror = false;

		/** Try to get the named tree from the Hashtable. If no name is provided, use 'default'.
			If a tree matches, then it must already be populated.
		*/
		if(treeName == null || treeName.length() == 0) {
			treeName = "default";
		}
		if(trees.get(treeName) != null && reload == false) {
			return true;		// already populated
		}
		System.out.println("Populating tree from the database.");

		String[] labelColumns = csvToArray(labelCols);
		String[] urlColumns = csvToArray(urlCols);

		String[] labels = new String[30];	// hardwired maximum depth of 30 levels (!!)
		String[] urls = new String[30];	// hardwired maximum depth of 30 levels (!!)
		TreeNode tn[] = new TreeNode[30];
		
		String treeId;	//结果集中的主KEY
		String treeSubId; //结果集中的第二个KEY
		String label;
		String url;
		
		int nodeID = 1;
		String nextLessonId;
		boolean hasChild = false;

		Tree root;
		root = new Tree("root");

		TreeNode node = null;

		try {
			
			int i = 0, j = 0, k = 0;

			if (treeType.equalsIgnoreCase("code"))
			{
				
				beanGetdata bgd = new beanGetdata();
				bgd.executeSelect(sql);
				for (i=0; i<bgd.getRowCount(); i++)
				{
					treeId = bgd.getFieldValue(treeIndex, i);
					
					if (treeId.equals(treeRoot))
					{
						treeSubId = bgd.getFieldValue(treeSubIndex, i);
						label = bgd.getFieldValue(labelCols, i);
						url = bgd.getFieldValue(urlCols, i);
						
						tn[0] = new TreeNode(nodeID++, label, url); //暂定为节点
						
						for (j=0; j<bgd.getRowCount(); j++)
						{
							if (treeSubId.equals(bgd.getFieldValue(treeIndex, j)))
							{
								tn[0].addChild(new TreeLeaf(bgd.getFieldValue(labelCols, j), bgd.getFieldValue(urlCols, j)));
								hasChild = true;
							}
						}
						if (hasChild)
						{
							root.addChild(tn[0]);
							hasChild = false;
						}
						else
						{
							root.addChild(new TreeLeaf(label, url));
							nodeID --;
						}
					}
				}
			}
			else if (treeType.equalsIgnoreCase("separate"))
			{
				beanGetdata bgd = new beanGetdata();
				bgd.executeSelect(sql);
				for(i = 0; i < bgd.getRowCount(); i ++)
				{
					treeId = bgd.getFieldValue(treeIndex, i);
					label = bgd.getFieldValue(labelCols, i);
					url = bgd.getFieldValue(urlCols, i);
					
					if (treeId.length()<(treeDepth-1)*interval)
						throw new Exception("treeIndex \"" + treeId + "\"长度太短，数据格式错误！");
					
					String allZero = "";
					boolean isNode = false; //判断是不是节点
			
					for (k=0; k<treeId.length(); k++)
					{
						allZero = allZero + "0";
					}
					
					String endValue = treeId.substring((treeDepth-1) * interval, treeId.length());		
					String endZero = allZero.substring((treeDepth-1) * interval, treeId.length());
					if (endValue.equals(endZero))
						isNode = true;
						
					String midValue;
					String midZero;
					if(isNode)
					{
						for(j=0; j < treeDepth - 2; j++) 
						{
							midValue = treeId.substring((j + 1) * interval, treeId.length());
							midZero = allZero.substring((j + 1) * interval, allZero.length());
							if (midValue.equals(midZero))
								break;
						}
						tn[j] = new TreeNode(nodeID++, label, url);
						
						hasChild = false;
						if (i < bgd.getRowCount() - 1)
						{
							midValue = bgd.getFieldValue(treeIndex, i + 1).substring(0, j * interval);
							if ( midValue.equals(treeId.substring(0, j * interval)))
								hasChild = true;
						}
						
						if(j > 0)
						{
							if (tn[j-1] == null)
								throw new Exception("数据结构不正确，\"" + treeId + "\"无上级节点！");
							if ( hasChild )
							{
								tn[j-1].addChild(tn[j]);
								hasChild = false;
							}
							else
							{
								tn[j-1].addChild( new TreeLeaf( label, url ));
								nodeID--;
							}
						}
						else
						{
							if ( hasChild )
							{
								root.addChild(tn[j]);
								hasChild = false;
							}
							else
							{
								root.addChild( new TreeLeaf( label, url ));
								nodeID--;
							}
						}
						continue;
					}
					for(j=0; j < treeDepth - 1; j++) 
					{
						midValue = treeId.substring(j * interval, (treeDepth - 1) * interval);
						midZero = allZero.substring(j * interval, (treeDepth - 1) * interval);
						if (midValue.equals(midZero))
							break;
					}
					if(j > 0)
					{
						if (tn[j-1] == null)
							throw new Exception("数据结构不正确，\"" + treeId + "\"无上级节点！");
						tn[j-1].addChild( new TreeLeaf( label, url) );
					}
					else
						root.addChild(new TreeLeaf(label,url));
				}
			}
			else if (treeType.equalsIgnoreCase("coloum"))
			{
				beanGetdata bgd = new beanGetdata();
				StringBuffer bufSql = new StringBuffer(300);
	
				bufSql.append("select distinct ").append(labelCols).append(",").append(urlCols);
				bufSql.append(" from ").append(tableName);
				bufSql.append(" ").append(whereClause).append(" order by ").append(labelCols);
				System.out.println(bufSql);
				bgd.executeSelect(bufSql.toString());
				for (k=0; k<bgd.getRowCount(); k++) {	// iterate through the denormalised view
	
					/** This reading of a 'line' has been decoupled from the main populating logic below
					 *  so that different types of data source can be added.
					 */
					for(i=0; i < labelColumns.length; i++) {	// for each column
						if(((labels[i] = bgd.getFieldValue(labelColumns[i], k))) == null || labels[i].length() == 0)
							break;
						if((urls[i] = bgd.getFieldValue(urlColumns[i], k)) != null && urls[i].length() == 0)
							urls[i] = null;	// force empty url columns to null for easy testing later...
					}
	
					for(j=0; j < i-1; j++) {
	
						if(tn[j] == null || !labels[j].equals(tn[j].getName())) {	// then this is the first or a new item
							tn[j] = new TreeNode(nodeID++, labels[j], urls[j]);
							if(j > 0)
								tn[j-1].addChild(tn[j]);
							else
								root.addChild(tn[j]);
							continue;
						}
					}
					if(j > 0)
						tn[j-1].addChild( new TreeLeaf( labels[j], urls[j]) );
					else
						root.addChild(new TreeLeaf(labels[j],urls[j]));
				}
				
			}
			else
			{
				throw new Exception("目前没有此功能！错误原因是treeType为：" + treeType);
			}
						
					 
			trees.put(treeName, root);	// Store the tree in the static Hashtable
		}
		catch(Exception e)
		{
			System.out.println(e);
			sqlerror=true;
		} 
		return !sqlerror;
	}

	/**
	 * This method is just a call to the method below with a null argument, meaning that the tree
	 * shouldn't get it's state from the session.
	 */
	public final String renderHTML() {
		return renderHTML((HttpServletRequest)null);
	}

	/**
	 * This method first of all builds a Hashtable of open nodes from the request parameter
	 * for easy lookup later. It then submits the root node of the tree named in the bean
	 * parameter 'treeName' (or the default tree) for rendering as HTML. If a HttpServletRequest
	 * is supplied, the method will try to obtain '<treeName>.oldopen' from the session.
	 */

	public final String renderHTML( HttpServletRequest req ) {

		if(populateJdbc() == false)	{		// ensures the tree is populated
			return "<p><strong>The CDTreeBean has not been successfully initialised. Check the server's stdout for details.</strong></p>";
		}

		// Try to obtain 'oldopen' from the session if a request object was passed
		if(req != null) {
			HttpSession s = req.getSession(false);
			String tmp = s != null ? (String)s.getAttribute(treeName + ".oldopen") : null;
			if(tmp != null && tmp.length() > 0)
				oldopen = tmp;
		}

		// Make a hash table of the currently open nodes (from the URL parameter)

		StringTokenizer st = new StringTokenizer(oldopen, ":");
		String token;
		while(st.hasMoreElements()) {
			try {
				token = st.nextToken();
				openNodes.put(Integer.valueOf(token), token);
				// strb.append(token).append(":");
			} catch(NumberFormatException e) {
				System.out.println("Warning: TreeBean was passed dodgy parameters!");
			}
		}
		// now add the requested node to the open list (if not a close-request)
		if(open >= 0) {
			openNodes.put(new Integer(open), String.valueOf(open));
			// strb.append(open);
		} else {
			openNodes.remove(new Integer(-open));
		}
		StringBuffer strb = new StringBuffer();
		Enumeration on = openNodes.elements();
		while(on.hasMoreElements()) {
			strb.append(on.nextElement()).append(":");
		}
		this.oldopen = strb.toString();
		// Try to write 'oldopen' to the session if a request object was passed
		if(req != null) {
			HttpSession s = req.getSession(false);
			if(s != null)
				s.setAttribute(treeName + ".oldopen", oldopen);
		}

		StringBuffer html = new StringBuffer(renderHTML((Tree)trees.get(treeName)));

		return html.toString();
	}

	private boolean isVisible(TreeNode node) {
		if(openNodes.containsKey(new Integer(node.getId())))
			return true;
		return false;
	}

	/**
	 * Renders the specified tree as HTML by iterating over all it's children. Will call itself
	 * recursively for any visible node children.
	 */
	private final String renderHTML(Tree tree) {

		StringBuffer buf = new StringBuffer();

		buf.append("\n<TABLE border=\"0\"");
		if(treeStyle != null && treeStyle.length() > 0)
			buf.append(" class=\"").append(treeStyle).append("\"");
		buf.append(">\n");
		for( int i=0; i < tree.size(); i++ ) {
			TreeObject treeObject = tree.getChild(i);
			if( treeObject.getType() == Tree.NODE ) {
				TreeNode node = (TreeNode)treeObject;
				buf.append( renderNodeHTML(node) );
				if( isVisible(node) ) {
					buf.append("<tr><td>&nbsp;</td><td>").append( renderHTML( node.getChildren() ) ).append("</td></tr>");
				}
			}
			else {
				TreeLeaf leaf = (TreeLeaf)treeObject;
				buf.append( renderLeafHTML(leaf) );
			}
		}
		buf.append("\n</TABLE>\n");

		return buf.toString();
	}

	/**
	 * Renders the specified tree node as HTML.
	 */
	private final String renderNodeHTML(TreeNode node) {
		StringBuffer buf = new StringBuffer();
		boolean visible = isVisible(node);
		buf.append( "<tr><td><a ");
		if(absopen == node.getId())	// active link reference
			buf.append("name='A' ");
		if (treePage.indexOf("?")>0)
			buf.append("href='").append(treePage).append("&");
		else
			buf.append("href='").append(treePage).append("?");
		buf.append("oldopen=").append(oldopen).append("&open=")
			.append(visible ? -(node.getId()) : node.getId())
			.append("' target=\"_self\">" );
		if( visible ) {
			buf.append( "<img src='").append(openImage).append("' border='0'>" );
		}
		else {
			buf.append( "<img src='").append(closedImage).append("' border='0'>" );
		}
		buf.append( "</a>" );
		buf.append( "</td><td>" );
		String link = node.getLink();
		if( link != null ) {
			buf.append( "<a href='");
			if(leafPage != null && leafPage.length() > 0)	// if a leafPage is specified, then prepend to the link
				buf.append(leafPage);
			buf.append(link).append("' ");

			if(leafTarget != null && leafTarget.length() > 0)
				buf.append("target='").append(leafTarget).append("'");

			buf.append(">" ).append( node.getName() ).append( "</a>" );
		}
		else {
			buf.append( node.getName() );
		}
		buf.append( "</td></tr>" );
		return buf.toString();
	}

	/**
	 * Renders the specified tree leaf as HTML.
	 */
	private final String renderLeafHTML( TreeLeaf leaf ) {

		StringBuffer buf = new StringBuffer();
		buf.append( "<tr><td>").append("<img src='").append(leafImage).append("' border='0'>")
			.append("</td><td><a href='");

		if(leafPage != null && leafPage.length() > 0)	// if a leafPage is specified, then prepend to the link
			buf.append(leafPage);

		buf.append(leaf.getLink()).append("' ");

		if(leafTarget != null && leafTarget.length() > 0)
			buf.append("target='").append(leafTarget).append("'");
		buf.append(">").append( leaf.getName() ).append( "</a></td></tr>" );

		return buf.toString();
	}

	/**
	 * Accepts a comma separated list of tokens and returns them as a string array.
	 */

	private String[] csvToArray(String csv) {

		StringTokenizer st = new StringTokenizer(csv,",");
		String[] buf = new String[st.countTokens()];

		int i = 0;
		while(st.hasMoreTokens())
			buf[i++] = st.nextToken();

		return buf;
	}

	public String adjustScrollPosition() {
//		return "<SCRIPT> window.location.href=\"#A\"; if(window.scrollTo) scrollTo(0,(document.body ? document.body.scrollTop : pageYOffset) - 20); </SCRIPT>";
		return "<SCRIPT> window.location.href=\"#A\"; window.scroll(0, -20); </SCRIPT>";
	}

	public static Hashtable getTrees() {
		return (CSTreeBean.trees); 
	}

	public static void setTrees(Hashtable trees) {
		CSTreeBean.trees = trees; 
	}

	public Hashtable getOpenNodes() {
		return (this.openNodes); 
	}

	public void setOpenNodes(Hashtable openNodes) {
		this.openNodes = openNodes; 
	}

	public String getTreeType() {
		return (this.treeType); 
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType; 
	}

	public int getTreeDepth() {
		return (this.treeDepth); 
	}

	public void setTreeDepth(int treeDepth) {
		this.treeDepth = treeDepth; 
	}

	public int getInterval() {
		return (this.interval); 
	}

	public void setInterval(int interval) {
		this.interval = interval; 
	}

	public String getTreeIndex() {
		return (this.treeIndex); 
	}

	public void setTreeIndex(String treeIndex) {
		this.treeIndex = treeIndex; 
	}

	public String getTreeSubIndex() {
		return (this.treeSubIndex); 
	}

	public void setTreeSubIndex(String treeSubIndex) {
		this.treeSubIndex = treeSubIndex; 
	}

	public String getTreeRoot() {
		return (this.treeRoot); 
	}

	public void setTreeRoot(String treeRoot) {
		this.treeRoot = treeRoot; 
	}

	public String getTreeTitle() {
		return (this.treeTitle); 
	}

	public void setTreeTitle(String treeTitle) {
		//判断是不是SQL语句
		if (treeTitle.toUpperCase().startsWith("SELECT")&&treeTitle.toUpperCase().indexOf("FROM")>0)
		{
			beanGetdata bgd = new beanGetdata();
			bgd.executeSelect(treeTitle);
			if (treeTitle.indexOf(",")>0) //该标题是一个超链接
			{
				treeTitle = "<A href=" + bgd.getFieldValue(2, 0) + ">" + bgd.getFieldValue(1, 0) + "</A>";
			}
			else
			{
				treeTitle = bgd.getFieldValue(1, 0);
			}
		}
		this.treeTitle = treeTitle; 
	}


	public String getTreeName() {
		return (this.treeName); 
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName; 
	}

	public String getTableName() {
		return (this.tableName); 
	}

	public void setTableName(String tableName) {
		this.tableName = tableName; 
	}

	public String getLabelCols() {
		return (this.labelCols); 
	}

	public void setLabelCols(String labelCols) {
		this.labelCols = labelCols; 
	}

	public String getUrlCols() {
		return (this.urlCols); 
	}

	public void setUrlCols(String urlCols) {
		this.urlCols = urlCols; 
	}

	public String getWhereClause() {
		return (this.whereClause); 
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause; 
	}

	public String getSql() {
		return (this.sql); 
	}

	public void setSql(String sql) {
		this.sql = sql; 
	}

	public String getTreePage() {
		return (this.treePage); 
	}

	public void setTreePage(String treePage) {
		this.treePage = treePage; 
	}

	public String getLeafPage() {
		return (this.leafPage); 
	}

	public void setLeafPage(String leafPage) {
		this.leafPage = leafPage; 
	}

	public String getLeafTarget() {
		return (this.leafTarget); 
	}

	public void setLeafTarget(String leafTarget) {
		this.leafTarget = leafTarget; 
	}

	public String getOpenImage() {
		return (this.openImage); 
	}

	public void setOpenImage(String openImage) {
		this.openImage = openImage; 
	}

	public String getClosedImage() {
		return (this.closedImage); 
	}

	public void setClosedImage(String closedImage) {
		this.closedImage = closedImage; 
	}

	public String getLeafImage() {
		return (this.leafImage); 
	}

	public void setLeafImage(String leafImage) {
		this.leafImage = leafImage; 
	}

	public String getTreeStyle() {
		return (this.treeStyle); 
	}

	public void setTreeStyle(String treeStyle) {
		this.treeStyle = treeStyle; 
	}

	public int getOpen() {
		return (this.open); 
	}

	public void setOpen(int open) {
		this.open = open; 
	}

	public int getAbsopen() {
		return (this.absopen); 
	}

	public void setAbsopen(int absopen) {
		this.absopen = absopen; 
	}

	public String getOldopen() {
		return (this.oldopen); 
	}

	public void setOldopen(String oldopen) {
		this.oldopen = oldopen; 
	}

	public boolean getReload() {
		return (this.reload); 
	}

	public void setReload(boolean reload) {
		this.reload = reload; 
	}

	public String toString() {
		String ret = null;
		ret = "trees = " + trees + "\n";
		ret += "openNodes = " + openNodes + "\n";
		ret += "treeType = " + treeType + "\n";
		ret += "treeDepth = " + treeDepth + "\n";
		ret += "interval = " + interval + "\n";
		ret += "treeIndex = " + treeIndex + "\n";
		ret += "treeSubIndex = " + treeSubIndex + "\n";
		ret += "treeRoot = " + treeRoot + "\n";
		ret += "treeTitle = " + treeTitle + "\n";
		ret += "treeName = " + treeName + "\n";
		ret += "tableName = " + tableName + "\n";
		ret += "labelCols = " + labelCols + "\n";
		ret += "urlCols = " + urlCols + "\n";
		ret += "whereClause = " + whereClause + "\n";
		ret += "sql = " + sql + "\n";
		ret += "treePage = " + treePage + "\n";
		ret += "leafPage = " + leafPage + "\n";
		ret += "leafTarget = " + leafTarget + "\n";
		ret += "openImage = " + openImage + "\n";
		ret += "closedImage = " + closedImage + "\n";
		ret += "leafImage = " + leafImage + "\n";
		ret += "treeStyle = " + treeStyle + "\n";
		ret += "open = " + open + "\n";
		ret += "absopen = " + absopen + "\n";
		ret += "oldopen = " + oldopen + "\n";
		ret += "reload = " + reload + "\n";
		return ret;
	}


	

}
