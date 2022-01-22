import { Component, OnInit } from "@angular/core";
import { ApiEndpoints, HttpService } from "../../services/http.service";
import { HttpRequest, HttpResponse } from "@angular/common/http";
import { Announcement } from "../../types/announcement";
import { QueryResponse } from "../../types/queryResponse";

@Component({
    selector: "app-dashboard",
    templateUrl: "./dashboard.component.html",
    styleUrls: ["./dashboard.component.scss"]
})
export class DashboardComponent implements OnInit {
    public announcements: Announcement[] = [];

    constructor(private http: HttpService) {
    }

    ngOnInit(): void {
        this.fetchAnnouncements()
    }

    private fetchAnnouncements() {
        const request = new HttpRequest(ApiEndpoints.AnnouncementsList.method, ApiEndpoints.AnnouncementsList.url, null);
        this.http.performRequest<QueryResponse<Announcement>>(request)
            .subscribe((response: HttpResponse<QueryResponse<Announcement>>) => {
                if (!response.body ) {
                    return;
                }

                this.announcements = response.body.values;
            });
    }

}
