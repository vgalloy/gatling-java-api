package com.vgalloy.gatlingjavaapi.server.model;

import java.io.Serializable;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleModel implements Serializable {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "SimpleModel{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
