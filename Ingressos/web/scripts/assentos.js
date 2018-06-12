'use strict';

const SECTOR_BORDER = 2,
      SEAT_BORDER = 2;

const Temp = {
    CANVAS_W: 500,
    CANVAS_H: 500,

    generateSeats: function(w, h) {
        var ret = Array(w);

        for (let i=0; i<w; i++) {
            ret[i] = Array(h);
            for (let j=0; j<h; j++) {
                ret[i][j] = Math.floor(Math.random() * 2);
            }
        }

        return ret;
    }
}

const SeatStatus = {
    FREE: 0,
    TAKEN: 1,
    RESERVED: 2
}

class Sector {
    constructor(opts) {
        this.x = opts.x;
        this.y = opts.y;
        this.width = opts.width;
        this.height = opts.height;

        this.rowCount = opts.rowCount;
        this.rowSize = opts.rowSize;
        this.seats = opts.seats

        this._seatW = this.width/this.rowSize;
        this._seatH = this.height/this.rowCount;
    }

    draw(opts) {
        // se o click não foi dentro do setor
        if (!!opts.click && !this._isClickInside(opts.click.x, opts.click.y, opts.width, opts.height))
            return;

        var x = this.x * opts.width,
            y = this.y * opts.height,
            width = this.width * opts.width,
            height = this.height * opts.height;

        // desenha o retângulo do setor
        opts.ctx.fillStyle = '#AAAAAA';
        opts.ctx.fillRect(x, y, width, height);
        opts.ctx.fillStyle = '#DDDDDD';
        opts.ctx.fillRect(x + SECTOR_BORDER, y + SECTOR_BORDER, width - SECTOR_BORDER*2, height - SECTOR_BORDER*2);

        // troca o estado do assento clicado
        if (opts.click) {
            let coords = this._getSeatCoordinates(opts.click.x, opts.click.y, opts.width, opts.height);
            if (this.seats[coords.y][coords.x] == SeatStatus.FREE)
                this.seats[coords.y][coords.x] = SeatStatus.RESERVED;
            else if (this.seats[coords.y][coords.x] == SeatStatus.RESERVED)
                this.seats[coords.y][coords.x] = SeatStatus.FREE;
        }

        // desenha os assentos
        for (let row = 0; row<this.rowCount; row++) {
            for (let col = 0; col<this.rowSize; col++) {
                this._drawSeat(opts.ctx, row, col, opts.width, opts.height);
            }
        }
    }

    _isClickInside(x, y, w, h) {
        return x >= this.x * w && x <= (this.x + this.width) * w &&
               y >= this.y * h && y <= (this.y + this.height) * h;
    }

    _getSeatCoordinates(x, y, screenW, screenH) {
        let normalizedX = x - this.x * screenW,
            normalizedY = y - this.y * screenH;

        return {
            x: Math.floor(normalizedX / (this._seatW * screenW)),
            y: Math.floor(normalizedY / (this._seatH * screenH))
        };
    }

    _drawSeat(ctx, row, col, screenW, screenH) {
        let x = this.x + this._seatW * col,
            y = this.y + this._seatH * row;
        ctx.fillStyle = '#000000';
        ctx.fillRect(x * screenW + SEAT_BORDER, 
                     y * screenH + SEAT_BORDER, 
                     this._seatW * screenW - SEAT_BORDER*2, 
                     this._seatH * screenH - SEAT_BORDER*2);

        switch (this.seats[row][col]) {
            case SeatStatus.FREE:
                ctx.fillStyle = '#00FF00';
                break;
            
            case SeatStatus.TAKEN:
                ctx.fillStyle = '#FF0000';
                break;

            case SeatStatus.RESERVED:
                ctx.fillStyle = '#0000FF';
        }

        ctx.fillRect(x * screenW + SEAT_BORDER*2, 
                     y * screenH + SEAT_BORDER*2, 
                     this._seatW * screenW - SEAT_BORDER*4, 
                     this._seatH * screenH - SEAT_BORDER*4);
    }
}

const sectors = [
    new Sector({
        x: 0,
        y: 0,
        width: 0.4,
        height: 0.4,
        rowCount: 10,
        rowSize: 10,
        seats: Temp.generateSeats(10, 10)
    }),
    new Sector({
        x: 0.6,
        y: 0,
        width: 0.4,
        height: 0.4,
        rowCount: 10,
        rowSize: 10,
        seats: Temp.generateSeats(10, 10)
    }),
    new Sector({
        x: 0,
        y: 0.5,
        width: 1,
        height: 0.5,
        rowCount: 12,
        rowSize: 20,
        seats: Temp.generateSeats(12, 20)
    })
];

var canvas, ctx;

window.onload = function() {
    canvas = document.createElement('canvas');
    canvas.width = Temp.CANVAS_W;
    canvas.height = Temp.CANVAS_H;
    document.body.appendChild(canvas);

    ctx = canvas.getContext('2d');

    // desenha tudo inicialmente
    sectors.forEach((sector) => {
        sector.draw({
            ctx: ctx,
            width: canvas.width,
            height: canvas.height,
            click: false
        });
    });

    canvas.onmousedown = function(e) {
        sectors.forEach((sector) => {
            sector.draw({
                ctx: ctx,
                width: canvas.width,
                height: canvas.height,
                click: {
                    x: e.pageX - this.offsetLeft,
                    y: e.pageY - this.offsetTop
                }
            });
        });
    };
}