function show() {
    document.querySelector(".background").className = "background show";
  }

  function close() {
    document.querySelector(".background").className = "background";
  }

  document.querySelector(".js-board-item").addEventListener("click", show);
  document.querySelector("#close").addEventListener("click", close);