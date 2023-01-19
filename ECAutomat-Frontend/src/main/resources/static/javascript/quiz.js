      var fs = require('browserify-fs');
      global.window.score = 0;
      const startButton = document.getElementById('startBtn')
      const k1button = document.getElementById('k1-btn')
      const k2button = document.getElementById('k2-btn')
      const k3button = document.getElementById('k3-btn')
      const highscoreButton = document.getElementById('highscoreBtn')
      const modebuttons = document.getElementById('mode')
      const modebutton1 = document.getElementById('m1-btn')
      const modebutton2 = document.getElementById('m2-btn')
      const katdiv = document.getElementById('kid')
      const trueBtn = document.getElementById("trueBtn")
      const falseBtn = document.getElementById("falseBtn")
      const questionContainerElement = document.getElementById('question-container')
      const startMenuElement = document.getElementById('startDiv')
      const questionElement = document.getElementById('q-btn')
      const timeButton = document.getElementById('timeBtn')
      const pointsButton = document.getElementById('pointsBtn')
      const backButton = document.getElementById('back-btn')
      const repeatButton = document.getElementById('repeatBtn')
      const quitButton = document.getElementById('quitBtn')
      const gameoverScreen = document.getElementById('gameoverScreen')
      const gameoverText = document.getElementById('gameoverText-btn')
      const gameoverPoints = document.getElementById('gameoverPoints-btn')
      const highscoreScreen = document.getElementById('highscoreScreen')
      const currentHs = document.getElementById('currentHs')
      //Booleans für die drei verschiedenen Kategorien, k1 = Allgemeinwissen, k2 = Wirtschaft, k3 = Technik
      let k1 = false;
      let k2 = false;
      let k3 = false;
      let points = 0;   // Aktueller Score
      let isSpeedmode = false;  //Boolean zur Unterscheidung zwischen den Modi
      let currentPage = 1;
      var timer;
      var minutes;
      var seconds;
      let obj;
      let fetchString = '../../../../../../ECAutomat-Backend/src/main/resources/static/quiz.json';
      var interval;
      let randomNumber;
      let currentHsNum; // Aktueller Highscore in der jeweiligen Kategorie und dem jeweiligen Modus
      let gameoverTimeout; // Zeitlimit für den Zeitmodus in Sekunden

      fetch(fetchString)
        .then(res => res.json())
        .then(data => {
          obj = data;
        })
        .then(() => {
          //Eventlistener für die Wahr/Falsch Buttons
          let randomIndex;
          trueBtn.addEventListener('click', () => {
            let anser = isCorrect(true, obj.questions[randomNumber].answer);
            if (anser) {
              // Die Buttons werden an dieser Stelle für 1,5 Sekunden gesperrt, damit der Nutzer die Antworten nicht spammen kann
              trueBtn.disabled = true;
              falseBtn.disabled = true;
              setTimeout(() => {
                trueBtn.disabled = false;
                falseBtn.disabled = false;
                trueBtn.style.backgroundColor = "#0066B3";
                // An dieser Stelle wird die nächste Frage geladen
                randomIndex = getRandomNumber(0, numbersArray.length - 1);
                randomNumber = numbersArray[randomIndex];
                numbersArray.splice(randomIndex, 1);
                questionElement.innerText = obj.questions[randomNumber].question;
              }, "1500")
              trueBtn.style.backgroundColor = "#B4C424"
              window.score++;
              points++;
              pointsButton.innerText = points;
              falseBtn.style.backgroundColor = "#0066B3";
            } else {
              clearInterval(interval);
              questionElement.innerText = "Wahre Aussage: " + obj.questions[randomNumber].rightAnswer;
              trueBtn.style.backgroundColor = "Red"
              // Die Buttons werden an dieser Stelle für 1,5 Sekunden gesperrt, damit der Nutzer die Antworten nicht spammen kann
              trueBtn.disabled = true;
              falseBtn.disabled = true;
              setTimeout(() => {
                if (!isSpeedmode) {
                  gameOver(points);
                }
                trueBtn.disabled = false;
                falseBtn.disabled = false;
                countdown(); // Damit der Timer nach dem TimeOut wieder weiterläuft
                trueBtn.style.backgroundColor = "#0066B3";
                falseBtn.style.backgroundColor = "#0066B3";
                // An dieser Stelle wird die nächste Frage geladen
                randomIndex = getRandomNumber(0, numbersArray.length - 1);
                randomNumber = numbersArray[randomIndex];
                numbersArray.splice(randomIndex, 1);
                questionElement.innerText = obj.questions[randomNumber].question;
              }, "3000")
            }
          })

          falseBtn.addEventListener('click', () => {
            let anser = isCorrect(false, obj.questions[randomNumber].answer);
            if (anser) {
              // Die Buttons werden an dieser Stelle für 1,5 Sekunden gesperrt, damit der Nutzer die Antworten nicht spammen kann
              trueBtn.disabled = true;
              falseBtn.disabled = true;
              setTimeout(() => {
                falseBtn.style.backgroundColor = "#0066B3";
                trueBtn.disabled = false;
                falseBtn.disabled = false;
                randomIndex = getRandomNumber(0, numbersArray.length - 1);
                randomNumber = numbersArray[randomIndex];
                numbersArray.splice(randomIndex, 1);
                questionElement.innerText = obj.questions[randomNumber].question;
              }, "1500")
              falseBtn.style.backgroundColor = "#B4C424"
              points++;
              window.score++;
              pointsButton.innerText = points;
              trueBtn.style.backgroundColor = "#0066B3";
            } else {
              clearInterval(interval);
              questionElement.innerText = "Die Aussage ist richtig.";
              falseBtn.style.backgroundColor = "Red"
              trueBtn.disabled = true;
              falseBtn.disabled = true;
              setTimeout(() => {
                if (!isSpeedmode) {
                  gameOver(points);
                }
                trueBtn.disabled = false;
                falseBtn.disabled = false;
                countdown(); // Damit der Timer nach dem TimeOut wieder weiterläuft
                trueBtn.style.backgroundColor = "#0066B3";
                falseBtn.style.backgroundColor = "#0066B3";
                // An dieser Stelle wird die nächste Frage geladen
                randomIndex = getRandomNumber(0, numbersArray.length - 1);
                randomNumber = numbersArray[randomIndex];
                numbersArray.splice(randomIndex, 1);
                questionElement.innerText = obj.questions[randomNumber].question;
              }, "3000")
            }
            if (numbersArray.length == 0) {
              gameOver(points);
            }
          })

        });

      k1button.addEventListener('click', () => {
        k1 = true;
        k2 = false;
        k3 = false;
        hideCategory();
        numbersArray = createArrayOfNumbers(0, 100); // Je nach Kategorie, wird auf unterschiedliche Bereiche der quiz.json Datei zugegriffen
        getScore();
      })

      k2button.addEventListener('click', () => {
        k1 = false;
        k2 = true;
        k3 = false;
        hideCategory();
        numbersArray = createArrayOfNumbers(100, 200); // Je nach Kategorie, wird auf unterschiedliche Bereiche der quiz.json Datei zugegriffen
      })

      k3button.addEventListener('click', () => {
        k1 = false;
        k2 = false;
        k3 = true;
        hideCategory();
        numbersArray = createArrayOfNumbers(200, 300); // Je nach Kategorie, wird auf unterschiedliche Bereiche der quiz.json Datei zugegriffen
      })


      startButton.addEventListener('click', () => {
        currentPage++;
        startMenuElement.classList.add('hide')
        katdiv.classList.remove('hide');
      })


      modebutton1.addEventListener('click', () => {
        currentPage++;
        timeBtn.classList.add('hide')
        setCurrentScore(isSpeedmode) // Hier wird der gerade benötigte Highscore geladen, damit er während dem Quiz angezeigt werden kann
        startGame();
      })

      modebutton2.addEventListener('click', () => {
        currentPage++;
        timeBtn.classList.remove('hide')
        isSpeedmode = true;
        setCurrentScore(isSpeedmode)
        startGame();
      })

      highscoreButton.addEventListener('click', () => {
        currentPage = 5;
        highscoreScreen.classList.remove('hide');
        window.score = 0;
        katdiv.classList.add('hide')
        startDiv.classList.add('hide');
      })
      
      // Je nachdem auf welcher Seite wir uns befinden, wird der setScreen-Methode ein anderer Wert mitgegeben. Auf diese Weise können wir rückwärts navigieren
      backButton.addEventListener('click', () => {
        if (currentPage == 1) {
          window.location.href = 'startseite.html'; //Link auf die Startseite
        }
        else if (currentPage == 2) {
          setScreen(2);
        } else if (currentPage == 3) {
          setScreen(3);
        } else if (currentPage == 4) {
          currentPage -= 3;
          setScreen(4);
        } else if (currentPage == 5) {
          setScreen(5);
        }
      })

// Zurücksetzen auf den Kategorie Bildschirm
      repeatButton.addEventListener('click', () => {
        setScreen(4);
        setScreen(3);
      })

// Verlässt das Quiz und bringt den Nutzer wieder auf die Startseite
      quitButton.addEventListener('click', () => {
        window.location.href = 'startseite.html';
      })


// innerhalb von startGame werden alle wichtigen Variablen zurückgesetzt um ein Spiel auf den Ursprungszustand zurückzusetzten
      function startGame() {
        resetTime();
        countdown();
        //Laden der ersten Frage
        let randomIndex = getRandomNumber(0, numbersArray.length - 1);
        randomNumber = numbersArray[randomIndex];
        numbersArray.splice(randomIndex, 1);
        pointsButton.innerText = 0;
        window.score = 0;
        points = 0;
        questionElement.innerText = obj.questions[randomNumber].question;
        modebuttons.classList.add('hide')
        questionContainerElement.classList.remove('hide')
        currentHs.classList.remove('hide');
        questionContainerElement.style.display = 'block';
        gameoverScreen.classList.add('hide');
        // Starten des Timers, falls wir uns im Zeitmodus befinden
        if (isSpeedmode) {
          gameoverTimeout = setTimeout(() => {
            gameOver();
          }, "121000")
        }
      }

      // Aktuelle Seite wird auf 3 gesetzt und die Kategorien verschwinden vom Bildschirm
      function hideCategory() {
        currentPage = 3;
        k1button.classList.add('hide')
        k2button.classList.add('hide')
        k3button.classList.add('hide')
        modebuttons.classList.remove('hide')
      }
// Zurücksetzen des Timers bei einem Neustart
      function resetTime() {
        timeButton.innerText = "2:00";
        timer = timeButton.innerHTML;
        timer = timer.split(':');
        minutes = timer[0];
        seconds = timer[1];
      }

// Hilfsmethode um zu überprufen, ob die gegebene Antwort der richtigen Antwort entspricht
      function isCorrect(answer, realanswer) {
        return answer == realanswer;
      }

    // Hilfsmethode für unseren Backbutton, die je nach der Seite auf der wir uns gerade befinden die Elemente sichtbar/unsichtbar macht
      function setScreen(page) {
        if (page == 2) {
          currentPage = 1;
          katdiv.classList.add('hide');
          startDiv.classList.remove('hide')
        }
        else if (page == 3) {
          currentPage = 2;
          modebuttons.classList.add('hide')
          k1button.classList.remove('hide')
          k2button.classList.remove('hide')
          k3button.classList.remove('hide')
        } else if (page == 4) {
          currentPage = 3;
          isSpeedmode = false;
          clearTimeout(gameoverTimeout) // stellt sicher, dass der Gameover-Timer gestoppt wird, wenn wir das Quiz vorzeitig über den Backbutton verlassen
          gameoverScreen.classList.add('hide');
          questionContainerElement.classList.add('hide')
          currentHs.classList.add('hide');
          modebuttons.classList.remove('hide')
        } else if (page == 5) {
          currentPage = 1;
          highscoreScreen.classList.add('hide')
          startDiv.classList.remove('hide')
        }
      }

// Hilfsmethode um den Highscore der aktuellen Kategorie/Modus Kombination zu laden
      function setCurrentScore(isSpeedmode) {
        getScore();
        if (isSpeedmode) {
          if (k1) {
            currentHsNum = genHsBtnTime.innerHTML;
            currentHs.innerText = "Highscore: " + genHsBtnTime.innerHTML;
          } else if (k2) {
            currentHsNum = ecoHsBtnTime.innerHTML;
            currentHs.innerText = "Highscore: " + ecoHsBtnTime.innerHTML;
          } else {
            currentHsNum = techHsBtnTime.innerHTML;
            currentHs.innerText = "Highscore: " + techHsBtnTime.innerHTML;
          }
        }
        if (!isSpeedmode) {
          if (k1) {
            currentHsNum = genHsBtn.innerHTML;
            currentHs.innerText = "Highscore: " + genHsBtn.innerHTML;
          } else if (k2) {
            currentHsNum = ecoHsBtn.innerHTML;
            currentHs.innerText = "Highscore: " + ecoHsBtn.innerHTML;
          } else {
            currentHsNum = techHsBtn.innerHTML;
            currentHs.innerText = "Highscore: " + techHsBtn.innerHTML;
          }
        }
      }
// Timer-Methode
      function countdown() {
        clearInterval(interval);
        interval = setInterval(function () {
          seconds -= 1;
          if (minutes < 0) return;
          else if (seconds < 0 && minutes != 0) {
            minutes -= 1;
            seconds = 59;
          }
          else if (seconds < 10) seconds = '0' + seconds;

          timeButton.innerHTML = minutes + ':' + seconds;

          if (minutes == 0 && seconds == 0) clearInterval(interval);
        }, 1000);
      }

      // Erstellen einer zufälligen Zahl
      function getRandomNumber(min, max) {
        let step1 = max - min + 1;
        let step2 = Math.random() * step1;
        let result = Math.floor(step2) + min;
        return result;
      }
      // Hilfsmethode um ein Array aus Zahlen zu erstellen. Die Parameter definieren die Range unseres Zahlenarrays
      function createArrayOfNumbers(start, end) {
        let myArray = [];
        for (let i = start; i <= end; i++) {
          myArray.push(i);
        }
        return myArray;
      }
      let numbersArray = createArrayOfNumbers(1, 100);

// Hilfsmethode für die Darstellung des Gameover-Bildschirms
      function gameOver(nScore) { //nScore ist der erzielte Score in der Runde
        gameoverScreen.classList.remove('hide');
        currentHs.classList.add('hide');
        questionContainerElement.style.display = 'none';
        gameoverPoints.innerHTML = nScore;
        if (nScore > currentHsNum) { // Falls der Highscore gebrochen wird
          gameoverText.innerText = "Neuer Highscore! Glückwunsch!"
        } else {
          if (nScore <= 1) { gameoverText.innerText = "Weißt du etwa nicht wie ein Touchscreen funktioniert?" }
          else if (nScore <= 5) { gameoverText.innerText = "Das war nichts!" }
          else if (nScore <= 10) { gameoverText.innerText = "Da geht aber noch mehr!" }
          else if (nScore <= 15) { gameoverText.innerText = "Nicht schlecht, aber da ist noch Luft nach oben!" }
          else if (nScore <= 20) { gameoverText.innerText = "Dieses Ergebnis lässt sich doch sehen!" }
          else if (nScore <= 25) { gameoverText.innerText = "Gut gemacht!" }
          else if (nScore <= 30) { gameoverText.innerText = "Starke Leistung!" }
          else if (nScore <= 50) { gameoverText.innerText = "Du bist ein Genie!" }
          else if (nScore <= 70) { gameoverText.innerText = "Uns gehen noch die Fragen aus!" }
          else if (nScore <= 90) { gameoverText.innerText = "Wo hast du die Lösungen her?!" }
          else if (nScore >= 100) { gameoverText.innerText = "Uns sind die Fragen ausgegangen!" }
        }
        setScore(); // Damit der Highscore ggf. aktualisiert werden kann
      }

      if (timeButton.innerText === "0:00") {
        questionContainerElement.style.display = 'none';
      }

// in dieser Methode wird die JSON-Datei für den Highscore erstellt und mit Initialwerten befüllt, falls sie nicht schon existiert
      window.createFile = function () {
        fs.readFile('score.json', 'utf-8', function (err, data) {
          if (err) {
            fs.writeFile('score.json', "{ \"highscores\": { \"genScore1\": 0, \"genScore2\": 0, \"ecoScore1\": 0, \"ecoScore2\": 0, \"techScore1\": 0, \"techScore2\": 0 } }", function () {
            });
          } else {
            console.log("File exists already")
          }
        });
      }
   // in setScore wird der Highscore überschrieben, falls er in der jeweiligen Runde gebrochen wurde
      window.setScore = function () {
        fs.readFile('score.json', 'utf-8', function (err, data) { // lesen der Highscore-Datei
          let currentScores = JSON.parse(data); // Parsen der Highscore-Datei in eine variable
          let newScores;
          if (k1) {
            if (isSpeedmode) {
              if (score > currentScores.highscores.genScore2) {
                currentScores.highscores.genScore2 = score;
              }
            } else if (score > currentScores.highscores.genScore1)
              currentScores.highscores.genScore1 = score;
          }
          if (k2) {
            if (isSpeedmode) {
              if (score > currentScores.highscores.ecoScore2) {
                currentScores.highscores.ecoScore2 = score;
              }
            } else if (score > currentScores.highscores.ecoScore1)
              currentScores.highscores.ecoScore1 = score;
          }
          if (k3) {
            if (isSpeedmode) {
              if (score > currentScores.highscores.techScore2) {
                currentScores.highscores.techScore2 = score;
              }
            } else if (score > currentScores.highscores.techScore1)
              currentScores.highscores.techScore1 = score;
          }
          let scoreString = JSON.stringify(currentScores);
          fs.writeFile('score.json', scoreString); // Rückschreiben der aktualisierten Highscores in die JSON-Datei
        });
      }
      // auslesen der Highscores aus der score.json-Datei
      window.getScore = function () {
        fs.readFile('score.json', 'utf-8', function (err, data) {
          let student = JSON.parse(data);
          genHsBtn.innerHTML = student.highscores.genScore1;
          genHsBtnTime.innerHTML = student.highscores.genScore2;
          ecoHsBtn.innerHTML = student.highscores.ecoScore1;
          ecoHsBtnTime.innerHTML = student.highscores.ecoScore2;
          techHsBtn.innerHTML = student.highscores.techScore1;
          techHsBtnTime.innerHTML = student.highscores.techScore2;
        });
      }
      // Diese Funktion kann dazu genutzt werden, um die Highscores zurückzusetzen
      window.deleteScore = function () {
        fs.unlink('score.json', (err) => {
          if (err) {
            throw err;
          }
          console.log("Score deleted");
        });
      }

    }).call(this)
  }).call(this, typeof global !== "undefined" ? global : typeof self !== "undefined" ? self : typeof window !== "undefined" ? window : {})
}, { "browserify-fs": 53 }]
}, { }, [153]);
