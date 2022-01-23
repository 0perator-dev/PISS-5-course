import { Component, Input, OnInit } from "@angular/core";
import { Announcement } from "../../../types/announcement";
import { ServerUrl } from "../../../resources/application.properties";

@Component({
    selector: "app-announcement-card",
    templateUrl: "./announcement-card.component.html",
    styleUrls: ["./announcement-card.component.scss"]
})
export class AnnouncementCardComponent implements OnInit {

    @Input()
    public announcement: Announcement | undefined;
    ServerUrl: string = ServerUrl;

    constructor() {
    }

    ngOnInit(): void {
    }

}
