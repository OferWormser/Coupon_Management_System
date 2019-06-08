import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/adminComponents/admin/admin.component';
import { CustomerComponent } from './components/customerComponents/customer/customer.component';
import { CompanyComponent } from './components/companyComponents/company/company.component';
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
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { LayoutComponent } from './components/layout/layout.component';
import { Page404Component } from './components/page404/page404.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    AdminComponent,
    CustomerComponent,
    CompanyComponent,
    CreateCompanyComponent,
    GetCompanyComponent,
    CompaniesComponent,
    CreateCustomerComponent,
    GetCustomerComponent,
    CustomersComponent,
    CouponsComponent,
    CreateCouponComponent,
    GetCouponComponent,
    GetCouponsByTypeComponent,
    GetCouponsByDateComponent,
    GetCouponsByPriceComponent,
    PurchaseCouponComponent,
    GetAllPurchasedCouponsComponent,
    GetPurchasedCouponsByTypeComponent,
    GetPurchasedCouponsByDateComponent,
    GetPurchasedCouponsByPriceComponent,
    LayoutComponent,
    Page404Component,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientJsonpModule
  ],
  providers: [],
  bootstrap: [LayoutComponent]
})
export class AppModule { }
