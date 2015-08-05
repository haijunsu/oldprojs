<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" omit-xml-declaration="yes"/>
<xsl:template match="/doc">
	<!-- 获取页面变量 -->
	<xsl:variable name="isindexall" select="isindexall"/>
	<xsl:variable name="isindexpage" select="isindexpage"/>
	<xsl:variable name="topic" select="topic"/>
	<xsl:variable name="catalog" select="catalog"/>
	<xsl:variable name="home">
		<a  href="/index.xml">技术专题</a>
	</xsl:variable>

	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=gb2312"
				/>
			<!-- 从XML文件中取出TITLE -->
			<title>
				<xsl:value-of select="title"/>
			</title>
			<link rel="stylesheet" href="/css/leftNav.css" type="text/css" />
		</head>
		<body>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td nowrap="nowrap" background="/bmp/bg.jpg" valign="middle">
						<div id="siteName">
							<!-- 从/navigator.xml中取出网站名称 -->
							<xsl:value-of
								select="document('/navigator.xml')/doc/siteName"
								disable-output-escaping="yes"/>
						</div>
					</td>
					<td valign="bottom"
						style="border-bottom-style: solid; border-bottom-width: 1px; border-bottom-color: #0055ff"
						nowrap="nowrap" background="/bmp/bg.jpg">
						<div id="globalNav">
							<!-- 从/navigator.xml中顶部菜单 -->
							<xsl:value-of
								select="document('/navigator.xml')/doc/globalNav"
								disable-output-escaping="yes"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" valign="top"
						style="border-right-style:solid; border-right-width:1px; border-right-color: #0055ff">
						<table width="100%" height="100%" bgcolor="#0055ff"
							border="0" cellspacing="0" cellpadding="0">
							<tr>
								<!-- 从/navigator.xml中取出导航栏 -->
								<td nowrap="nowrap">
									<div id="navBar">
										<xsl:choose>
											<!-- 没有指定专题，显示首页 -->
											<xsl:when test="$topic = ''">
												<div id="sectionLinks">
													<h3>
														<xsl:value-of
															select="document('/navigator.xml')/doc/navbar/topics/title"/>
													</h3>
													<ul>
														<xsl:for-each
															select="document('/navigator.xml')/doc/navbar/topics/topic">
														<xsl:sort select="seq" />
															<li>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/begin"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="url"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/end"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="title"/>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/close"
																	disable-output-escaping="yes"/>
															</li>
														</xsl:for-each>
													</ul>
												</div>
											</xsl:when>
											<!-- 显示指定的专题 -->
											<xsl:otherwise>
												<!-- 指定的专题导航置于最上层 -->
												<xsl:for-each
													select="document('/navigator.xml')/doc/navbar/topics/topic[id=$topic]">
													<div id="sectionLinks">
														<h3>
															<xsl:value-of
																select="title"/>
														</h3>
														<ul>
															<xsl:for-each
																select="item">
															<xsl:sort select="seq" />

																<li>

																		<xsl:value-of
																		select="document('/navigator.xml')/doc/linktag/begin"
																		disable-output-escaping="yes"/>

																		<xsl:value-of
																		select="url"
																		disable-output-escaping="yes"/>

																		<xsl:value-of
																		select="document('/navigator.xml')/doc/linktag/end"
																		disable-output-escaping="yes"/>

																		<xsl:value-of
																		select="title"/>

																		<xsl:value-of
																		select="document('/navigator.xml')/doc/linktag/close"
																		disable-output-escaping="yes"/>
																</li>
															</xsl:for-each>
														</ul>
													</div>
												</xsl:for-each>
												<!-- 其它专题列表 -->
												<div id="sectionLinks2">
													<h3>
														<xsl:value-of
															select="document('/navigator.xml')/doc/navbar/topics/title"/>
													</h3>
													<ul>
														<xsl:for-each
															select="document('/navigator.xml')/doc/navbar/topics/topic[id!=$topic]">
															<xsl:sort
																select="seq" />
															<li>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/begin"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="url"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/end"
																	disable-output-escaping="yes"/>
																<xsl:value-of
																	select="title"/>
																<xsl:value-of
																	select="document('/navigator.xml')/doc/linktag/close"
																	disable-output-escaping="yes"/>
															</li>
														</xsl:for-each>
													</ul>
												</div>
											</xsl:otherwise>
										</xsl:choose>
									</div>
								</td>
							</tr>
							<xsl:if test="$topic = ''">
								<tr>
									<td>
										<div id="navBar">
											<div id="sectionLinks2">
												<!-- 从/navigator.xml中取出其他联接，仅在首页中显示 -->
												<h3>
													<xsl:value-of
														select="document('/navigator.xml')/doc/navbar/relatedLinks/title"/>
												</h3>
												<ul>
													<xsl:for-each
														select="document('/navigator.xml')/doc/navbar/relatedLinks/link">
														<li>
															<xsl:value-of
																select="document('/navigator.xml')/doc/linktag/begin"
																disable-output-escaping="yes"/>
															<xsl:value-of
																select="url"
																disable-output-escaping="yes"/>
															<xsl:value-of
																select="document('/navigator.xml')/doc/linktag/end"
																disable-output-escaping="yes"/>
															<xsl:value-of
																select="title"/>
															<xsl:value-of
																select="document('/navigator.xml')/doc/linktag/close"
																disable-output-escaping="yes"/>
														</li>
													</xsl:for-each>
												</ul>
											</div>
										</div>
									</td>
								</tr>
							</xsl:if>
						</table>
					</td>
					<td width="80%" valign="top">
						<!-- 当前所处的专题位置 -->
						<div id="breadCrumb"> 当前位置：
							<xsl:copy-of select="$home" />
							<xsl:if
							test="$topic != ''"> / <xsl:for-each
							select="document('/navigator.xml')/doc/navbar/topics/topic[id=$topic]">
							<xsl:value-of
							select="document('/navigator.xml')/doc/linktag/begin"
							disable-output-escaping="yes"/> <xsl:value-of
							select="url" disable-output-escaping="yes"/>
							<xsl:value-of
							select="document('/navigator.xml')/doc/linktag/end"
							disable-output-escaping="yes"/> <xsl:value-of
							select="title"/> <xsl:value-of
							select="document('/navigator.xml')/doc/linktag/close"
							disable-output-escaping="yes"/> <xsl:if
							test="$catalog != ''"> <xsl:for-each
							select="document('/navigator.xml')/doc/navbar/topics/topic/item[id=$catalog]">
							/ <xsl:value-of
							select="document('/navigator.xml')/doc/linktag/begin"
							disable-output-escaping="yes"/> <xsl:value-of
							select="url" disable-output-escaping="yes"/>
							<xsl:value-of
							select="document('/navigator.xml')/doc/linktag/end"
							disable-output-escaping="yes"/> <xsl:value-of
							select="title"/> <xsl:value-of
							select="document('/navigator.xml')/doc/linktag/close"
							disable-output-escaping="yes"/> </xsl:for-each>
							</xsl:if> </xsl:for-each> </xsl:if> </div>
						<div id="pageName">
							<!-- 页面名称 -->
							<xsl:value-of select="pagename"/>
						</div>
						<!-- 页面内容 -->
						<div id="content">
							<xsl:value-of select="content"
								disable-output-escaping="yes"/>
						</div>
						<!-- 如果是topic是空而catalog为sitemap，则在这里输出站点地图 -->
						<xsl:if test="($catalog='sitemap') and ($topic='')">
						<div id="sitemap">
							<xsl:copy-of select="$home" /><br />
								<xsl:for-each
									select="document('/navigator.xml')/doc/navbar/topics/topic">
								<xsl:sort select="seq" />
									<xsl:if test="position()&lt;last()">
									<xsl:value-of
										select="document('/navigator.xml')/doc/sitemap/firstnode"
										disable-output-escaping="yes"/>
									</xsl:if>
									<xsl:if test="position()=last()">
									<xsl:value-of
										select="document('/navigator.xml')/doc/sitemap/firstendnode"
										disable-output-escaping="yes"/>
									</xsl:if>
									<xsl:value-of
										select="document('/navigator.xml')/doc/linktag/begin"
										disable-output-escaping="yes"/>
									<xsl:value-of select="url"
										disable-output-escaping="yes"/>
									<xsl:value-of
										select="document('/navigator.xml')/doc/linktag/end"
										disable-output-escaping="yes"/>
									<xsl:value-of select="title"/>
									<xsl:value-of
										select="document('/navigator.xml')/doc/linktag/close"
										disable-output-escaping="yes"/>
									<br />
									<xsl:if test="position()&lt;last()">
									<xsl:for-each select="item">
									<xsl:sort select="seq" />
										<xsl:if test="position()&lt;last()">
										<xsl:value-of
											select="document('/navigator.xml')/doc/sitemap/secondnode"
											disable-output-escaping="yes"/>
										</xsl:if>
										<xsl:if test="position()=last()">
										<xsl:value-of
											select="document('/navigator.xml')/doc/sitemap/secondendnode"
											disable-output-escaping="yes"/>
										</xsl:if>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/begin"
											disable-output-escaping="yes"/>
										<xsl:value-of select="url"
											disable-output-escaping="yes"/>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/end"
											disable-output-escaping="yes"/>
										<xsl:value-of select="title"/>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/close"
											disable-output-escaping="yes"/>
										<br />
									</xsl:for-each>
									</xsl:if>
									<xsl:if test="position()=last()">
									<xsl:for-each select="item">
									<xsl:sort select="seq" />
										<xsl:if test="position()&lt;last()">
										<xsl:value-of
											select="document('/navigator.xml')/doc/sitemap/thirdnode"
											disable-output-escaping="yes"/>
										</xsl:if>
										<xsl:if test="position()=last()">
										<xsl:value-of
											select="document('/navigator.xml')/doc/sitemap/thirdendnode"
											disable-output-escaping="yes"/>
										</xsl:if>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/begin"
											disable-output-escaping="yes"/>
										<xsl:value-of select="url"
											disable-output-escaping="yes"/>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/end"
											disable-output-escaping="yes"/>
										<xsl:value-of select="title"/>
										<xsl:value-of
											select="document('/navigator.xml')/doc/linktag/close"
											disable-output-escaping="yes"/>
										<br />
									</xsl:for-each>
									</xsl:if>
								</xsl:for-each>
						</div>
						</xsl:if>
						<!-- 如果是索引页面，这里输出更新列表 -->
						<xsl:if test="$isindexpage = 'true'">
							<div id="updateLinks">
								<!-- 首页列表 -->
								<xsl:if test="$topic=''">
									<ul>
										<xsl:if test="$isindexall = 'false'">
											<!-- 输出前20个列表 -->
											<xsl:for-each
												select="document('/update.xml')/doc/update/item[position()&lt;21]">
													<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
													select="date" />]
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/begin"
													disable-output-escaping="yes"/>
													/<xsl:value-of
													select="topic"
													/>/<xsl:value-of
													select="catalog"
													/>/<xsl:value-of
													select="file" />
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/end"
													disable-output-escaping="yes"/>
													<xsl:value-of
													select="name"/>
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/close"
													disable-output-escaping="yes"/>
													</li>
											</xsl:for-each>
										</xsl:if>
										<xsl:if test="$isindexall = 'true'">
											<!-- 输出全部列表 -->
											<xsl:for-each
												select="document('/update.xml')/doc/update/item">
												<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
													select="date" />]
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/begin"
													disable-output-escaping="yes"/>
													/<xsl:value-of
													select="topic"
													/>/<xsl:value-of
													select="catalog"
													/>/<xsl:value-of
													select="file" />
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/end"
													disable-output-escaping="yes"/>
													<xsl:value-of
													select="name"/>
													<xsl:value-of
													select="document('/navigator.xml')/doc/linktag/close"
													disable-output-escaping="yes"/>
													</li>
											</xsl:for-each>
										</xsl:if>
									</ul>
								</xsl:if>
								<!-- 输出专题 -->
								<xsl:if test="$topic!=''">
									<xsl:if test="$catalog=''">
										<!-- 一级专题 -->
										<ul>
											<xsl:if
												test="$isindexall = 'false'">
											<!-- 输出前20个列表 -->
												<xsl:for-each
													select="document('/update.xml')/doc/update/item[(topic=$topic)]">
													<!-- 由于for-each循环中没有办法取到过滤后的前20条记录，只好暂时显示全部记录
													select="document('/update.xml')/doc/update/item[(position()&lt;21) and (topic=$topic)]">
													-->
												<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
														select="date" />]
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/begin"
														disable-output-escaping="yes"/>
														/<xsl:value-of
														select="topic"
														/>/<xsl:value-of
														select="catalog"
														/>/<xsl:value-of
														select="file" />
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/end"
														disable-output-escaping="yes"/>
														<xsl:value-of
														select="name"/>
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/close"
														disable-output-escaping="yes"/>
														</li>
												</xsl:for-each>
											</xsl:if>
											<xsl:if test="$isindexall = 'true'">
											<!-- 输出全部列表 -->
												<xsl:for-each
													select="document('/update.xml')/doc/update/item[topic=$topic]">
												<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
														select="date" />]
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/begin"
														disable-output-escaping="yes"/>
														/<xsl:value-of
														select="topic"
														/>/<xsl:value-of
														select="catalog"
														/>/<xsl:value-of
														select="file" />
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/end"
														disable-output-escaping="yes"/>
														<xsl:value-of
														select="name"/>
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/close"
														disable-output-escaping="yes"/>
														</li>
												</xsl:for-each>
											</xsl:if>
										</ul>
									</xsl:if>
									<xsl:if test="$catalog!=''">
										<!-- 二级专题 -->
										<ul>
											<xsl:if
												test="$isindexall = 'false'">
												<!-- 输出前20个列表 -->
												<xsl:for-each
													select="document('/update.xml')/doc/update/item[(topic=$topic) and (catalog=$catalog)]">
													<!-- 由于for-each循环中没有办法取到过滤后的前20条记录，只好暂时显示全部记录
													select="document('/update.xml')/doc/update/item[(position()&lt;21) and (topic=$topic) and (catalog=$catalog)]">
													-->
												<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
														select="date" />]
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/begin"
														disable-output-escaping="yes"/>
														/<xsl:value-of
														select="topic"
														/>/<xsl:value-of
														select="catalog"
														/>/<xsl:value-of
														select="file" />
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/end"
														disable-output-escaping="yes"/>
														<xsl:value-of
														select="name"/>
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/close"
														disable-output-escaping="yes"/>
														</li>
												</xsl:for-each>
											</xsl:if>
											<xsl:if test="$isindexall = 'true'">
											<!-- 输出全部列表 -->
												<xsl:for-each
													select="document('/update.xml')/doc/update/item[(topic=$topic) and (catalog=$catalog)]">
												<xsl:sort select="date" order="descending"/>
													<li> [<xsl:value-of
														select="date" />]
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/begin"
														disable-output-escaping="yes"/>
														/<xsl:value-of
														select="topic"
														/>/<xsl:value-of
														select="catalog"
														/>/<xsl:value-of
														select="file" />
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/end"
														disable-output-escaping="yes"/>
														<xsl:value-of
														select="name"/>
														<xsl:value-of
														select="document('/navigator.xml')/doc/linktag/close"
														disable-output-escaping="yes"/>
														</li>
												</xsl:for-each>
											</xsl:if>
										</ul>
									</xsl:if>
								</xsl:if>
							</div>
						</xsl:if>
						<!-- 显示全部列表链接页 -->
						<xsl:if test="$isindexpage = 'true'">
							<xsl:if test="$isindexall = 'false'">
								<div id="moreLinks"><xsl:value-of
									select="document('/navigator.xml')/doc/linktag/begin"
									disable-output-escaping="yes"/>
									<xsl:value-of select="indexfile"
									disable-output-escaping="yes"/>
									<xsl:value-of
									select="document('/navigator.xml')/doc/linktag/end"
									disable-output-escaping="yes"/>- 全部文档列表
									-<xsl:value-of
									select="document('/navigator.xml')/doc/linktag/close"
									disable-output-escaping="yes"/></div>
							</xsl:if>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<td
						style="border-top-style: solid; border-top-width: 1px; border-top-color: #0055ff"
						colspan="2">
						<div id="siteInfo" align="center">
							<!-- 从/navigator.xml中取出版权信息 -->
							<xsl:value-of
								select="document('/navigator.xml')/doc/copyright"
								disable-output-escaping="yes"/>
						</div>
					</td>
				</tr>
			</table>
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>

