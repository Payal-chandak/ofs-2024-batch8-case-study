/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
import * as AccUtils from "../accUtils";
import * as ko from "knockout";
import "oj-c/input-number";
import 'oj-c/form-layout';
import "oj-c/input-text";
import 'oj-c/form-layout';
import 'oj-c/input-password';
import "oj-c/button";

class DashboardViewModel {
  firstname : ko.Observable<any>;
  lastname : ko.Observable<any>;
  gender : ko.Observable<any>;
  email: ko.Observable<any>;
  mobile: ko.Observable<any>;
  customer_status : ko.Observable<any>;
  login_id : ko.Observable<any>;
  password : ko.Observable<any>;



    constructor(){
        this.firstname = ko.observable(null);
        this.lastname = ko.observable(null);
        this.gender = ko.observable(null);
        this.email = ko.observable(null);
        this.mobile = ko.observable(null);
        this.customer_status = ko.observable(null);
        this.login_id = ko.observable(null);
        this.password = ko.observable(null);
    }

    public handleClick = async (event: Event) => {
        // alert('Button was clicked!');
        // this.clickedButton('Clicked');
        // let id = parseInt(this.firstName());
        let url = 'http://localhost:8081/registration/newcustomer';
        const row = {
          "firstName": this.firstname(),
          "lastName": this.lastname(),
          "gneder": this.gender(),
          "email": this.email(),
          "mobile": this.mobile(),
          "customerStatus": this.customer_status(),
          login: {
            "loginId": this.login_id(),
            "password": this.password()}
        };
        console.log(row);
        const request = new Request(url, {
          headers: new Headers({
            "Content-Type": "application/json",
          }),
          body: JSON.stringify(row),
          method: "POST",
        });
        const response = await fetch(request);
        console.log(await response.json());
      }

    
    }


export = DashboardViewModel;

