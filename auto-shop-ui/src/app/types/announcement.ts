export interface ImageEntity {
    id?: string,
    imgPath: string
}

export interface Announcement {
    id?: number,
    images?: ImageEntity[],
    title: string,
    description?: string
}
