import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Character } from '../models/character';
import { MarvelCharService } from '../services/marvel-char.service';
import { Comment } from '../models/comment';
import { WebShareService } from '../webshare/web-share.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit, OnDestroy{

  charId =  "";
  param$! :  Subscription;
  character! : Character;
  comments!: Comment[];

  constructor(private activatedRoute: ActivatedRoute, 
    private marvelCharSvc: MarvelCharService, private router: Router
      , private webshareSvc: WebShareService ){

  }

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
       async (params) => {
        this.charId = params['charId'];
        console.log(this.charId);
        const l = await this.marvelCharSvc.getCharactersDetails(this.charId);
        this.character = l.details;
        const ll = await this.marvelCharSvc.getCharComments(this.charId);
        console.log(ll);
        this.comments = ll;
        
      }
    );

  }

  addComent(){
    const queryParams: Params = { charParam: this.character['name'] + '|' + this.character.id };
    this.router.navigate(['/comment'], {queryParams : queryParams})
  }

  share(){
    this.webshareSvc.share({
      title: 'Marvel App',
      text: 'Hey check out my Share',
      url: 'https://developers.google.com/web'
    });
  }

  ngOnDestroy(): void{
    this.param$.unsubscribe();
  }

}
