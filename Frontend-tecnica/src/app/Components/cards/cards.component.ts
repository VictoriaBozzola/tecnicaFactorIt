import { Component, Input, OnInit, Output } from '@angular/core';
import { EventEmitter } from 'protractor';
import { Producto } from 'src/app/interfaces/carrito';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {

  @Input() infoProducto: Producto;
  constructor() { }

  ngOnInit(): void {
  }

}
