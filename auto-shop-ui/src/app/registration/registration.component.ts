import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
      this.httpService.getRegistrationView();
  }

}
