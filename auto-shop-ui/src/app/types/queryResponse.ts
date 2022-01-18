export interface QueryResponse<T> {
    pageSize: number;
    page: number;
    totalPages: number;
    values: Array<T>;
}
