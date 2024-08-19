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
import "oj-c/input-text";
import 'oj-c/form-layout';
import 'oj-c/input-password';
import "oj-c/button";



class IncidentsViewModel {
  login_id : ko.Observable<any>;
  password : ko.Observable<any>;
  login_status : ko.Observable<any>;


  constructor() {
    this.login_id = ko.observable(null);
    this.password = ko.observable(null);
    this.login_status = ko.observable(null);
    }
    
    public LoginClick = async (event: Event) => {
      // alert('Button was clicked!');
      // this.clickedButton('Clicked');
      // let id = parseInt(this.firstName());
      let url = 'http://localhost:8081/login/create';
      const row = {
        "loginId": this.login_id(),
        "password": this.password(),
        "login_status": this.login_status(),
        
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

  

export = IncidentsViewModel;
