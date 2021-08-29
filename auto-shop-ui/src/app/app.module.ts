import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ClarityModule } from "@clr/angular";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { LoginComponent } from "./login/login.component";
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegistrationComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ClarityModule,
        BrowserAnimationsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
