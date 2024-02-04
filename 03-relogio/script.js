let paused = false;
let interval;

function updateClock() {
  const now = new Date();
  const hours = now.getHours().toString().padStart(2, "0");
  const minutes = now.getMinutes().toString().padStart(2, "0");
  const seconds = now.getSeconds().toString().padStart(2, "0");

  document.getElementById("hours").textContent = hours;
  document.getElementById("minutes").textContent = minutes;
  document.getElementById("seconds").textContent = seconds;
}

function toggleClock() {
  if (paused) {
    interval = setInterval(updateClock, 1000);
    document.getElementById("pauseResumeBtn").textContent = "Pausar";
  } else {
    clearInterval(interval);
    document.getElementById("pauseResumeBtn").textContent = "Continuar";
  }

  paused = !paused;
}

document.getElementById("pauseResumeBtn").addEventListener("click", toggleClock);

updateClock();
interval = setInterval(updateClock, 200);

// Get elements
const hoursElement = document.getElementById("hours");
const minutesElement = document.getElementById("minutes");
const secondsElement = document.getElementById("seconds");
const pauseResumeBtn = document.getElementById("pauseResumeBtn");
const markTimeBtn = document.getElementById("markTimeBtn");
const timeList = document.getElementById("timeList");

let isPaused = false;
let times = [];

setInterval(updateTime, 1000);
