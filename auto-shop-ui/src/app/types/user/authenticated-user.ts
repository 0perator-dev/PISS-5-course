import { ERole } from "./ERole";

export interface AuthenticatedUser {
    id: number;
    username: string;
    roles: Array<ERole>;
}
