const thumbnails = document.querySelectorAll(".video-thumbnail");

// 클릭 시 썸네일 펼치기 추가
thumbnails.forEach((thumbnail) => {
  thumbnail.addEventListener("click", () => {
    const videoId = thumbnail.dataset.videoId;
    const parentRow = thumbnail.closest("tr");
    let videoRow = parentRow.nextElementSibling;

    // 비디오 플레이어가 있으면 제거
    if (videoRow && videoRow.classList.contains("video-player-row")) {
      videoRow.remove();
      return;
    }

    videoRow = document.createElement("tr");
    videoRow.classList.add("video-player-row");
    videoRow.innerHTML = `<td colspan="4">
                          <div class="video-container" style="text-align:center;">
                            <iframe width="560" height="315"
                            src="https://www.youtube.com/embed/${videoId}"
                            frameborder="0" allowfullscreen>
                            </iframe>
                            </div>
                            </td>`;
    parentRow.after(videoRow);
  });
});