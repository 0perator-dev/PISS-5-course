export enum AlertType {
    info= "info", warning = "warning", success = "success", danger = "danger"
}

export interface AlertItem {
    message: string;
    alertType: AlertType;
}
