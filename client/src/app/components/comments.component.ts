import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MarvelCharService } from '../services/marvel-char.service';
import { Comment } from '../models/comment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit, OnDestroy{
  form!: FormGroup;
  queryParams$! :  Subscription;
  charParam!: any;
  charName! : string;
  charId!: string;

  constructor(private activatedRoute: ActivatedRoute,  private formBuilder: FormBuilder,
    private marvelCharSvc: MarvelCharService, private router: Router){

  }

  ngOnInit(): void {
    this.form = this.createForm();
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      (queryParams) => {
        this.charParam = queryParams['charParam'].split('|');
        console.log(this.charParam[0]);
        console.log(this.charParam[1]);
        this.charName = this.charParam[0];
        this.charId = this.charParam[1];
      }
    );

  }

  saveComment(){
    const commentFormVal = this.form?.value['comment'];
    const c = {} as Comment;
    c.comment = commentFormVal;
    c.id = this.charId;

    this.marvelCharSvc.saveComment(c);
    this.router.navigate(['/details', this.charId]);
  }

  cancel(){
    this.router.navigate(['/details', this.charId]);
  }

  ngOnDestroy(): void{
    this.queryParams$.unsubscribe();
  }

  private createForm(): FormGroup{
    return this.formBuilder.group({
      comment : this.formBuilder.control(''),  
    })
  }
}
