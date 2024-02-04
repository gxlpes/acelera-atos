class HashGame {
  constructor() {
    this.resetGame();
  }

  resetGame() {
    this.cells = Array(9).fill(null);
    this.currentPlayer = "X";
    this.winner = null;
    this.playerXName = "";
    this.playerOName = "";
  }

  startGame() {
    do {
      this.playerXName = prompt("Digite o nome do Jogador X") || "Jogador X";
    } while (!this.playerXName.trim());
    do {
      this.playerOName = prompt("Digite o nome do Jogador O") || "Jogador O";
    } while (!this.playerOName.trim());

    alert(`Jogador X: ${this.playerXName}\nJogador O: ${this.playerOName}`);
    document.getElementById("startGame").style.display = "none";
    document.getElementById("app").style.display = "grid";
  }

  handleCellClick(index) {
    if (!this.cells[index] && !this.winner) {
      this.cells[index] = this.currentPlayer;
      this.renderCell(index);
      this.checkWinner();

      if (this.winner === "Tie") {
        alert("Empate!");
        this.resetGame();
        document.getElementById("startGame").style.display = "block";
        document.getElementById("app").style.display = "none";
      } else if (this.winner) {
        const playAgain = confirm(`Fim de jogo!\n${this.getPlayerName(this.winner)} ganhou!\n\nDeseja jogar novamente?`);
        if (playAgain) {
          this.resetGame();
          this.startGame();
        } else {
          document.getElementById("startGame").style.display = "block";
          document.getElementById("app").style.display = "none";
        }
      } else {
        this.currentPlayer = this.currentPlayer === "X" ? "O" : "X";
      }
    }
  }

  checkWinner() {
    const winningCombos = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];

    for (const combo of winningCombos) {
      const [a, b, c] = combo;
      if (this.cells[a] && this.cells[a] === this.cells[b] && this.cells[a] === this.cells[c]) {
        this.winner = this.currentPlayer;
        return;
      }
    }

    if (!this.cells.includes(null)) {
      this.winner = "Tie";
    }
  }

  renderCell(index) {
    const cellElement = document.getElementById(`cell${index}`);
    cellElement.textContent = this.cells[index];
  }

  getPlayerName(playerSymbol) {
    return playerSymbol === "X" ? this.playerXName : this.playerOName;
  }
}

const game = new HashGame();

document.getElementById("startButton").addEventListener("click", () => {
  game.startGame();
});

document.getElementById("app").addEventListener("click", (event) => {
  if (event.target.classList.contains("cell")) {
    const index = parseInt(event.target.id.slice(4));
    game.handleCellClick(index);
  }
});

window.addEventListener("beforeunload", () => {
  game.resetGame();
});
