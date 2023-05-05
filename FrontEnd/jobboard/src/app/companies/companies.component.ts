import { Component, OnInit } from '@angular/core';
import { Company } from '../models/company.model';
import { CompanyService } from '../services/company.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css'],
})
export class CompaniesComponent implements OnInit {
  companies: Company[] = [];
  searchText = '';
  sortAscending = true;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getCompanies().subscribe((companies) => {
      this.companies = companies;
    });
  }

  sortedCompanies(): Company[] {
    const companies = this.companies.filter((company) =>
      company.name.toLowerCase().includes(this.searchText.toLowerCase())
    );

    if (this.sortAscending) {
      return companies.sort((a, b) => a.name.localeCompare(b.name));
    } else {
      return companies.sort((a, b) => b.name.localeCompare(a.name));
    }
  }

  onSort(): void {
    this.sortAscending = !this.sortAscending;
  }
}
