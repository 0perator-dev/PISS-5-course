import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AnnouncementCardComponent } from "./common-components/announcement-card/announcement-card.component";
import { MainContainerComponent } from './main-container/main-container.component';
import { RouterModule, Routes } from "@angular/router";
import { ClarityModule } from "@clr/angular";

const declarations = [
    DashboardComponent,
    AnnouncementCardComponent,
    MainContainerComponent
];

const routes: Routes = [
    {path: "", component: DashboardComponent}
]

@NgModule({
    declarations,
    exports: [...declarations],
    imports: [
        CommonModule,
        RouterModule,
        ClarityModule,
        RouterModule.forChild(routes),
    ],
})
export class ComponentsModule {
}
