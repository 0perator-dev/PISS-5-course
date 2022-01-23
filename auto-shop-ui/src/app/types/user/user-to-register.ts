import { ERole } from "./ERole";

export interface UserToRegister {
    username: string;
    password: string;
    name: string;
    roles: Array<ERole>;
}
