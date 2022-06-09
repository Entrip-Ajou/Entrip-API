    var pesonality;
    var num = 1;
    var q = {
        1: {
            "title": "우연히 TV 속에서 한 여행지를 보았다. 그리고 그곳으로 여행을 떠날 생각에 설렘이 가득하다. 당신이 보고 있는 여행지는?",
            "type": "HA",
            "A": "예쁜 풍경과 힐링 요소가 많은 여행지",
            "B": "액티비티와 즐길 거리가 많은 여행지"
        },
        2: {
            "title": "바로 인터넷 검색을 시작한다. 당신이 중점적으로 생각하는 것은? ",
            "type": "PL",
            "A": "인스타그램 좋아요가 많은 스팟들을 분류한다",
            "B": "블로그를 중심으로 찐 로컬 스팟을 찾는다"
        },
        3: {
            "title": "당장 여행을 가고 싶지만 이번 주말 비행기 표 값이 만만치 않다.",
            "type": "FR",
            "A": "내가 가고 싶다면 비행기 값이 무슨 상관!",
            "B": "여행지 비 성수기를 노려 싼 티켓을 구매!"
        },
        4: {
            "title": "친구에게 같이 여행을 가자고 연락이 왔다. 당신이 같이 갈 친구는?",
            "type": "SI",
            "A": "계획표는 늘 내 손안에! j형 인간 민영",
            "B": "내가 가는 길이 곧 길이다~ 가서 생각하자! 즉흥적인 p형 인간 지우"
        },
        5: {
            "title": "여행 메이트와 톡방이 만들어졌다. 당신이 할 일은?",
            "type": "SI",
            "A": "나만 믿고 따라와~ 나의 주도하에 여행계획을 짠다",
            "B": "아우 귀찮아~ 난 군말없이 따라가기만 할께!"
        },
        6: {
            "title": "숙소 선정을 위한 최종 후보 2곳이 올라왔다. 당신의 선택은?",
            "type": "HA",
            "A": "아침에 일어나면 새소리와 나무 향을 잔뜩 느낄 수 있는 숲 뷰 숙소",
            "B": "열정 가득한 도시의 모든 전경을 볼 수 있는 시티뷰 숙소  "
        },
        7: {
            "title": "드디어 여행 날이 다가왔다. 당신의 행동은?",
            "type": "PL",
            "A": "출발과 도착의 설레는 마음을 모두 인스타 그램으로 기록해야지!",
            "B": "모든 정신은 여행을 즐기는 곳에만! 새로운 공간의 공기를 몸으로 느끼자!"
        },
        8: {
            "title": "가려고 했던 맛집에 사정이 생겨 문을 닫았다",
            "type": "SI",
            "A": "리뷰를 비교 분석해서 새로운 맛집을 찾아보자!",
            "B": "주변에 맛있어 보이는 집에 들어가자!"
        },
        9: {
            "title": "여행지 주변에 대형 놀이공원이 오픈했다는 소식을 들었다. 하지만 자유이용권의 가격은 너무나 비싼데…",
            "type": "FR",
            "A": "그래도 언제 가겠어! 이정도 퀄리티면 5만원 지불할 수 있지!",
            "B": "여행의 하루를 놀이공원으로 소비할 수 없어~ 입장권으로 구경만 하자!"
        },
        10: {
            "title": "시간 절약으로 계획했던 여행이 예상보다 3시간 일찍 끝났다. 숙소로 돌아가는 길에 한 곳을 방문할 수 있다면?",
            "type": "HA",
            "A": "이색체험과 힐링을 동시에 할 수 있는 공방 혹은 동물 카페",
            "B": "여행지의 풍경을 높은 심장박동수로 즐길 수 있는 집라인"
        },
        11: {
            "title": "오늘의 여행을 마무리하는 저녁식사! 당신의 선택은?",
            "type": "PL",
            "A": "인스타로 인싸 인증 가능한 아름다운 인테리어의 MZ세대 저격 맛집!",
            "B": "여행지 원주민들만 간다는 찐 로컬 맛집!"
        },
        12: {
            "title": "여행 마지막 날. 돌아가는 길에 여행지를 기념할 수 있는 각종 상품들이 눈에 밟힌다.",
            "type": "FR",
            "A": "고민보단 go! 가족들과 친구들에게 선물 그리고 여행을 추억하기 위해 기념품 싹쓸이",
            "B": "이미 우리 마음속에 여행지의 추억이 가득, 기념품은 사치야!"
        }
    }
    var result = {
        "SHPF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SHPR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SHLF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SHLR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SAPF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SAPR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SALF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "SALR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IHPF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IHPR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IHLF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IHLR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IAPF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IAPR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IALF": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        },
        "IALR": {
            "city": "우주인형",
            "explain": "설명",
            "img": "astronaut.png"
        }
    }

    function start() {
        $(".start").hide();
        $(".question").show();
        next();
    }
    $("#A").click(function() {
        var type = $("#type").val()
        var prevalue = $("#" + type).val();
        $("#" + type).val(parseInt(prevalue) + 1);
        next();
    });
    $("#B").click(function() {
        next();
    });

    function next() {
        if (num == 13) {
            $(".question").hide();
            $(".result").show();

            var mbti = ""
            if ($("#EI").val() < 2) {
                mbti = mbti + "I";
            } else {
                mbti = mbti + "S";
            }
            if ($("#SN").val() < 2) {
                mbti = mbti + "A";
            } else {
                mbti = mbti + "H";
            }
            if ($("#TF").val() < 2) {
                mbti = mbti + "L";
            } else {
                mbti = mbti + "P";
            }
            if ($("#JP").val() < 2) {
                mbti = mbti + "R";
            } else {
                mbti = mbti + "F";
            }
            $("#img").attr("src", result[mbti]["img"]);
            $("#city").html(result[mbti]["city"]);
            $("#explain").html(result[mbti]["explain"]);

            switch (mbti) {
                case "SHPF":
                    pesonality = 1;
                    break;
                case "SHPR":
                    pesonality = 2;
                    break;
                case "SHLF":
                    pesonality = 3;
                    break;
                case "SHLR":
                    pesonality = 4;
                    break;
                case "SAPF":
                    pesonality = 5;
                    break;
                case "SAPR":
                    pesonality = 6;
                    break;
                case "SALF":
                    pesonality = 7;
                    break;
                case "SALR":
                    pesonality = 8;
                    break;
                case "IHPF":
                    pesonality = 9;
                    break;
                case "IHPR":
                    pesonality = 10;
                    break;
                case "IHLF":
                    pesonality = 11;
                    break;
                case "IHLR":
                    pesonality = 12;
                    break;
                case "IAPF":
                    pesonality = 13;
                    break;
                case "IAPR":
                    pesonality = 14;
                    break;
                case "IALF":
                    pesonality = 15;
                    break;
                case "IALR":
                    pesonality = 16;
                    break;

            }
            var id = new URLSearchParams(location.search).get('user_id');
            var newurl = "api/v1/users/travelFavorite/";
            newurl = newurl + id + "/" + pesonality;
            fetch(newurl, {
                method: 'PUT',
                headers: {
                    'content-type': 'application/json'
                }
            })
        } else {
            $(".progress-bar").attr('style', 'width: calc(100/12*' + num + '%)');
            $("#title").html(q[num]["title"]);
            $("#type").val(q[num]["type"]);
            $("#A").html(q[num]["A"]);
            $("#B").html(q[num]["B"]);
            num++;
        }
    }