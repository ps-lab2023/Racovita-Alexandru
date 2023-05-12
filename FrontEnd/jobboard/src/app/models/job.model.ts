import { Company } from './company.model';
import { Category } from '../models/category.model'; // Update the path


export interface Job {
  id: number | null;
  title: string;
  description: string;
  company: Company;
  category: Category;


}
