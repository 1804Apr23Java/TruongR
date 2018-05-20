import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bears',
  templateUrl: './bears.component.html',
  styleUrls: ['./bears.component.css']
})
export class BearsComponent {
  bears = [{name:'InterpolatedBear', type:'Grizzly'}, {name:'', type:''}];

  //for interpolation
  interpolatedBear = this.bears[0].name;

  //for property and event binding
  public bearStyle = {
    color:'red',
    border:'1px solid black',
    margin:'2px'
  };

  public changeStyles(): void {
    if (this.bearStyle.color === 'red') {
      this.bearStyle.color = 'green';
    } else if (this.bearStyle.color === 'green') {
      this.bearStyle.color = 'red';
    } 
  }

  //for two-way binding
  //we'll set properties of this.bears[1] dynamically
}
