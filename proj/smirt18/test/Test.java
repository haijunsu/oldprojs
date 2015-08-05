import application.entities.Tparticipant;
import application.helper.BeansLocator;
import application.service.RegisterService;

/*
 * @(#)Test.java  2005-2-27
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */

/**
 * <p><b>Description</b></p>
 * <p></p>
 *
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class Test {

    public static void main(String[] args) {
      RegisterService rs = (RegisterService) BeansLocator.getBeanResource("registerService");
      Tparticipant _tparticipant = new Tparticipant();
      _tparticipant.setChvPartiEmail("mazzi@invap.com.ar");
      rs.createParticipant(_tparticipant);
     }
}
