import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy{
  form!: FormGroup;
  charName?: string;

  constructor(private formBuilder: FormBuilder, private router: Router,
    ){
  
  }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  ngOnDestroy(): void {
      
  }

  search(){
    const charName = this.form?.value['charName'];
    this.router.navigate(['/list', charName]);
  }

  private createForm(): FormGroup{
    return this.formBuilder.group({
      charName : this.formBuilder.control(''),  
    })
  }
}
