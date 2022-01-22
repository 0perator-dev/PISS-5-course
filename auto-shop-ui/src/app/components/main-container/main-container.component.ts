import { Component, OnInit } from '@angular/core';
import { AlertItem } from "../../types/alert-item";
import { NavGroup } from "../../types/nav-group";

@Component({
    selector: 'app-main-container',
    templateUrl: './main-container.component.html',
    styleUrls: ['./main-container.component.scss']
})
export class MainContainerComponent implements OnInit {
    public alerts: AlertItem[] = [];
    public sideNavGroups: NavGroup[] = [];

    constructor() {
    }

    ngOnInit(): void {
    }

}
