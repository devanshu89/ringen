/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-02-18 22:11:37 UTC)
 * on 2016-03-06 at 11:38:07 UTC 
 * Modify at your own risk.
 */

package com.appspot.ezrttsys.emperapi.model;

/**
 * Model definition for Employee.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the emperapi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Employee extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String email;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String employername;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String firstName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String inTime;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lastName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String mobile;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GeoPt offAddress;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String outTime;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer weekDayEnd;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer weekDayStart;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmail() {
    return email;
  }

  /**
   * @param email email or {@code null} for none
   */
  public Employee setEmail(java.lang.String email) {
    this.email = email;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmployername() {
    return employername;
  }

  /**
   * @param employername employername or {@code null} for none
   */
  public Employee setEmployername(java.lang.String employername) {
    this.employername = employername;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName firstName or {@code null} for none
   */
  public Employee setFirstName(java.lang.String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getInTime() {
    return inTime;
  }

  /**
   * @param inTime inTime or {@code null} for none
   */
  public Employee setInTime(java.lang.String inTime) {
    this.inTime = inTime;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLastName() {
    return lastName;
  }

  /**
   * @param lastName lastName or {@code null} for none
   */
  public Employee setLastName(java.lang.String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getMobile() {
    return mobile;
  }

  /**
   * @param mobile mobile or {@code null} for none
   */
  public Employee setMobile(java.lang.String mobile) {
    this.mobile = mobile;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public GeoPt getOffAddress() {
    return offAddress;
  }

  /**
   * @param offAddress offAddress or {@code null} for none
   */
  public Employee setOffAddress(GeoPt offAddress) {
    this.offAddress = offAddress;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getOutTime() {
    return outTime;
  }

  /**
   * @param outTime outTime or {@code null} for none
   */
  public Employee setOutTime(java.lang.String outTime) {
    this.outTime = outTime;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getStatus() {
    return status;
  }

  /**
   * @param status status or {@code null} for none
   */
  public Employee setStatus(java.lang.Boolean status) {
    this.status = status;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getWeekDayEnd() {
    return weekDayEnd;
  }

  /**
   * @param weekDayEnd weekDayEnd or {@code null} for none
   */
  public Employee setWeekDayEnd(java.lang.Integer weekDayEnd) {
    this.weekDayEnd = weekDayEnd;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getWeekDayStart() {
    return weekDayStart;
  }

  /**
   * @param weekDayStart weekDayStart or {@code null} for none
   */
  public Employee setWeekDayStart(java.lang.Integer weekDayStart) {
    this.weekDayStart = weekDayStart;
    return this;
  }

  @Override
  public Employee set(String fieldName, Object value) {
    return (Employee) super.set(fieldName, value);
  }

  @Override
  public Employee clone() {
    return (Employee) super.clone();
  }

}
