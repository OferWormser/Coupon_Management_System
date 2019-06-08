import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/adminComponents/admin/admin.component';
import { CompanyComponent } from './components/companyComponents/company/company.component';
import { CustomerComponent } from './components/customerComponents/customer/customer.component';
import { CreateCompanyComponent } from './components/adminComponents/create-company/create-company.component';
import { GetCompanyComponent } from './components/adminComponents/get-company/get-company.component';
import { CompaniesComponent } from './components/adminComponents/companies/companies.component';
import { CreateCustomerComponent } from './components/adminComponents/create-customer/create-customer.component';
import { GetCustomerComponent } from './components/adminComponents/get-customer/get-customer.component';
import { CustomersComponent } from './components/adminComponents/customers/customers.component';
import { CreateCouponComponent } from './components/companyComponents/create-coupon/create-coupon.component';
import { GetCouponComponent } from './components/companyComponents/get-coupon/get-coupon.component';
import { CouponsComponent } from './components/companyComponents/coupons/coupons.component';
import { GetCouponsByTypeComponent } from './components/companyComponents/get-coupons-by-type/get-coupons-by-type.component';
import { GetCouponsByDateComponent } from './components/companyComponents/get-coupons-by-date/get-coupons-by-date.component';
import { GetCouponsByPriceComponent } from './components/companyComponents/get-coupons-by-price/get-coupons-by-price.component';
import { PurchaseCouponComponent } from './components/customerComponents/purchase-coupon/purchase-coupon.component';
import { GetAllPurchasedCouponsComponent } from './components/customerComponents/get-all-purchased-coupons/get-all-purchased-coupons.component';
import { GetPurchasedCouponsByTypeComponent } from './components/customerComponents/get-purchased-coupons-by-type/get-purchased-coupons-by-type.component';
import { GetPurchasedCouponsByDateComponent } from './components/customerComponents/get-purchased-coupons-by-date/get-purchased-coupons-by-date.component';
import { GetPurchasedCouponsByPriceComponent } from './components/customerComponents/get-purchased-coupons-by-price/get-purchased-coupons-by-price.component';
import { Page404Component } from './components/page404/page404.component';

const routes: Routes = [
   {path: '', redirectTo: 'home', pathMatch: 'full'},
   {path: 'home', component: HomeComponent},
   {path: 'rest/admin', component: AdminComponent,
   children: [ 
      {path: '', redirectTo:'rest/admin', pathMatch: 'full'},
      {path: 'companies', component: CompaniesComponent},
      {path: 'createCompany', component: CreateCompanyComponent},
      {path: 'companies/:id', component: GetCompanyComponent},
      {path: 'customers', component: CustomersComponent},
      {path: 'createCustomer', component: CreateCustomerComponent},
      {path: 'customers/:id', component: GetCustomerComponent},
   ]},
   {path: 'rest/company', component: CompanyComponent,
   children: [
      {path: '', redirectTo:'rest/company', pathMatch: 'full'},
      {path: 'coupons', component: CouponsComponent},
      {path: 'createCoupon', component: CreateCouponComponent},
      {path: 'coupons/:id', component: GetCouponComponent},
      {path: 'getCouponsByType', component: GetCouponsByTypeComponent},
      {path: 'getCouponsByDate', component: GetCouponsByDateComponent},
      {path: 'getCouponsByPrice', component: GetCouponsByPriceComponent},
   ]},
   {path: 'rest/customer', component: CustomerComponent,
   children: [
      {path: '', redirectTo:'rest/customer', pathMatch: 'full'},
      {path: 'purchaseCoupon', component: PurchaseCouponComponent},
      {path: 'getAllPurchasedCoupons', component: GetAllPurchasedCouponsComponent},
      {path: 'getPurchasedCouponsByType', component: GetPurchasedCouponsByTypeComponent},
      {path: 'getPurchasedCouponsByDate', component: GetPurchasedCouponsByDateComponent},
      {path: 'getPurchasedCouponsByPrice', component: GetPurchasedCouponsByPriceComponent},
   ]}, 
   {path: "**", component: Page404Component} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
