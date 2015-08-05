/* GNU Server Pages - A free page compilation servlet
 * Copyright (C) 1998  Ed Korthof and James Cooper
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *
 * For more information about this software, visit:
 * http://www.bitmechanic.com/projects/
 */

package com.htyz.navy.sql;

import java.util.Vector;

/**
 * A simple queue implementation.  Internally stores data in a Vector.
 *
 * @author James Cooper
 * @version $Id: Queue.java,v 1.2 1999/08/25 06:20:08 pixel Exp $
 */
public class Queue {

  private Vector v;

  public Queue() {
    v = new Vector();
  }

  public Queue(int size) {
    v = new Vector(size);
  }

  public synchronized Object dequeue() {
    if(empty()) return null;
    else {
      Object o = v.firstElement();
      v.removeElementAt(0);
      return o;
    }
  }

  public void enqueue(Object o) {
    v.addElement(o);
  }

  public int size() {
    return v.size();
  }

  public boolean empty() {
    return v.size() < 1;
  }

}
