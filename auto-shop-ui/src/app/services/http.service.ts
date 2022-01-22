import { Injectable } from "@angular/core";
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";

export const ServerUrl = "http://localhost:8080";

export enum Methods {
    GET = "GET",
    POST = "POST",
    PUT = "PUT",
    DELETE = "DELETE"
}

export const ApiEndpoints = {
    AnnouncementsList: {
        url: ServerUrl + "/api/announcements",
        method: Methods.GET
    }
}

@Injectable({ providedIn: "root" })
export class HttpService {

    private url = ServerUrl;

    constructor(private httpClient: HttpClient) {
    }

    performRequest<T>(request: HttpRequest<any>): Observable<HttpResponse<T>> {
        const bearer = window.localStorage.getItem("Authentication");
        if(bearer !== null) {
            request.headers.append("Authentication", bearer)
        }

        // @ts-ignore
        return this.httpClient.request<T>(request);
    }

    isHttpRequest<T>(response: HttpEvent<T>): response is HttpResponse<T> {
        return response instanceof HttpResponse;
    }
}
