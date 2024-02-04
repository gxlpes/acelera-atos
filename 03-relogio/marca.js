function updateTime() {
  if (!isPaused) {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    hoursElement.textContent = hours;
    minutesElement.textContent = minutes;
    secondsElement.textContent = seconds;
  }
}

pauseResumeBtn.addEventListener("click", () => {
  isPaused = !isPaused;
  pauseResumeBtn.textContent = isPaused ? "Retomar" : "Pausar";
});

markTimeBtn.addEventListener("click", () => {
  const hours = hoursElement.textContent;
  const minutes = minutesElement.textContent;
  const seconds = secondsElement.textContent;
  const timeString = `${hours}:${minutes}:${seconds}`;
  times.push(timeString);
  renderTimeList();
});

function renderTimeList() {
  timeList.innerHTML = "";
  times.forEach((time) => {
    const li = document.createElement("li");
    li.textContent = time;
    timeList.appendChild(li);
  });
}
