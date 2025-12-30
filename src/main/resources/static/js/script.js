gsap.registerPlugin(ScrollTrigger);

// 1. 인트로 텍스트 줌인 & 페이드 아웃
gsap.to(".intro-text", {
    scale: 50,          // 엄청 크게 확대
    opacity: 0,         // 투명해짐
    ease: "power2.in",  // 가속도 붙으며 커짐
    scrollTrigger: {
        trigger: ".intro-section",
        start: "top top",
        end: "bottom top", // 섹션 높이만큼 스크롤할 때 애니메이션 진행
        scrub: 1,          // 스크롤 속도에 맞춰 부드럽게 재생
        pin: true,         // 섹션 고정 (핀)
        // markers: true   // 개발 중에만 켜서 트리거 위치 확인 가능
    }
});

// 2. 콘텐츠 섹션 등장 애니메이션
gsap.to(".content-section", {
    opacity: 1,
    duration: 1,
    scrollTrigger: {
        trigger: ".content-section",
        start: "top 80%", // 화면의 80% 지점에 도달했을 때 시작
        end: "top 50%",
        scrub: true
    }
});

// 3. 카드들이 순차적으로 올라오는 효과
gsap.from(".card", {
    y: 50, // 이동 거리를 100에서 50으로 줄여서 부담 완화
    opacity: 0,
    duration: 0.8,
    stagger: 0.2,
    scrollTrigger: {
        trigger: ".card-grid",
        start: "top 85%"
    },
    clearProps: "transform" // 애니메이션 끝나면 transform 속성 제거 (호버 효과와 충돌 방지)
});

// 4. 스크롤 유도 표시 페이드 아웃 (스크롤 시작하면 사라짐)
gsap.to(".scroll-indicator", {
    opacity: 0,
    duration: 0.5,
    scrollTrigger: {
        trigger: ".intro-section",
        start: "top top",     // 스크롤 시작하자마자
        end: "10% top",       // 조금만 내려도
        scrub: true           // 사라지게
    }
});

