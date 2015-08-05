/*
 * @(#)ModelFactory.java  2005-3-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package control;

import util.ResourcesUtil;
import util.StringUtil;
import model.IModel;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ModelFactory {
    
    public static IModel getFCA() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.tools.fca"));
        return _model;
    }
    
    public static IModel getConceptualGraphs() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.tools.conceptual.graphs"));
        return _model;
    }
    
    public static IModel getProtege() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.tools.protege"));
        return _model;
    }
    
    public static IModel getConnectingDatabase() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.database.connecting.database"));
        return _model;
    }
    public static IModel getListTables() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.database.list.table"));
        return _model;
    }
    public static IModel getQuery() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.database.query"));
        return _model;
    }
    public static IModel getOpenFile() {
        ModelFactory _factory = new ModelFactory();
        IModel _model = _factory.loadModel(ResourcesUtil.getProperty("model.file.open"));
        return _model;
    }
    
    private IModel loadModel(String modelName) {
        try {
            if (StringUtil.isBlankString(modelName)) {
                return null;
            }
            Class _class = getClass().getClassLoader().loadClass(modelName);
            IModel _model = (IModel) _class.newInstance();
            return _model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
