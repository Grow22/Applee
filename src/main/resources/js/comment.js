document.addEventListener('DOMContentLoaded', () => {

    const commentButtons = document.querySelectorAll('.comment-btn');

    commentButtons.forEach(button => {
        button.addEventListener('click', () => {
            const itemId = button.dataset.itemId;
            const commentSection = document.getElementById('comments-' + itemId);
            
            // 댓글 섹션이 현재 숨겨져 있으면 보이게 하고, 아니면 숨깁니다.
            if (commentSection.style.display === 'none' || commentSection.style.display === '') {
                commentSection.style.display = 'block';
                // 댓글 섹션이 보일 때만 댓글과 입력 폼을 로드
                loadComments(itemId, commentSection);
            } else {
                commentSection.style.display = 'none';
            }
        });
    });

    // 댓글 데이터를 로컬 스토리지에서 불러와 화면에 표시하는 함수
    const loadComments = (itemId, commentSection) => {
        // 기존 댓글 목록을 비웁니다.
        commentSection.innerHTML = `
            <p><strong>댓글 목록</strong></p>
        `;
        const commentsList = document.createElement('div');
        commentsList.id = `comments-list-${itemId}`;
        commentSection.appendChild(commentsList);
        
        // 로컬 스토리지에서 댓글 불러오기
        const allComments = JSON.parse(localStorage.getItem('comments')) || {};
        const itemComments = allComments[itemId] || [];

        itemComments.forEach(comment => {
            const p = document.createElement('p');
            p.textContent = comment.text;
            commentsList.appendChild(p);
        });

        // 댓글 입력 폼 추가
        const commentForm = document.createElement('div');
        commentForm.innerHTML = `
            <textarea id="comment-input-${itemId}" placeholder="댓글을 입력하세요..." style="width:100%; height: 50px;"></textarea>
            <button class="add-comment-btn">댓글 추가</button>
        `;
        commentSection.appendChild(commentForm);

        // 댓글 추가 버튼에 이벤트 리스너 추가
        const addCommentBtn = commentForm.querySelector('.add-comment-btn');
        addCommentBtn.addEventListener('click', () => {
            const commentInput = document.getElementById(`comment-input-${itemId}`);
            const commentText = commentInput.value.trim();

            if (commentText) {
                // 새로운 댓글 추가
                const newComment = {
                    id: Date.now(),
                    text: commentText
                };
                
                // 로컬 스토리지에 저장
                allComments[itemId] = allComments[itemId] || [];
                allComments[itemId].push(newComment);
                localStorage.setItem('comments', JSON.stringify(allComments));
                
                // 화면에 즉시 표시
                const p = document.createElement('p');
                p.textContent = newComment.text;
                commentsList.appendChild(p);

                // 입력 필드 초기화
                commentInput.value = '';
            }
        });
    };
});