export class Coupon {
    public constructor (
    public id?: number,
    public title?: string,
    public message?: string,
    public price?: number,
    public amount?: number,
    public startDate?: Date,
    public endDate?: Date,
    public type?: string,
    public image?: string) {}
 
}
