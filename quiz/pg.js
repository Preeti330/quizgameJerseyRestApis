// Questions that will be asked
/*
const Questions = [{
	q: "What is capital of India?",
	a: [{ text: "Gandhinagar", isCorrect: false },
	{ text: "Surat", isCorrect: false },
	{ text: "Delhi", isCorrect: true },
	{ text: "Mumbai", isCorrect: false }
	]

},
{
	q: "What is the capital of Thailand?",
	a: [{ text: "Lampang", isCorrect: false, isSelected: false },
	{ text: "Phuket", isCorrect: false },
	{ text: "Ayutthaya", isCorrect: false },
	{ text: "Bangkok", isCorrect: true }
	]

},
{
	q: "What is the capital of Gujarat",
	a: [{ text: "Surat", isCorrect: false },
	{ text: "Vadodara", isCorrect: false },
	{ text: "Gandhinagar", isCorrect: true },
	{ text: "Rajkot", isCorrect: false }
	]

}

]

*/

const getQue=localStorage.getItem("getQuestion");
var Questions=JSON.parse(getQue);
// console.log(Questions.length,"pg");

let currQuestion = 0
let score = 0
console.log(Questions[currQuestion]);

async function loadQuestion(){
   // console.log(typeof JSON.stringify(questionResponse));

    const question = document.getElementById("ques")
	const opt = document.getElementById("opt")
    
    question.textContent=Questions[currQuestion].question;
    var id=Questions[currQuestion].id;
    document.getElementById("ques").setAttribute("value",id);
    opt.innerHTML = "";
    
    for(let i=0;i<Questions[currQuestion].mc.length;i++){
        const choicesdiv = document.createElement("div");
		const choice = document.createElement("input");
		const choiceLabel = document.createElement("label");
        const ivalue=i+1;

		choice.type = "radio";
		choice.name = "answer";
		choice.value = ivalue;
        // console.log(Questions[currQuestion].mc[i].choices);
        // console.log(ivalue);
        choiceLabel.textContent = Questions[currQuestion].mc[i].choices;

		choicesdiv.appendChild(choice);
		choicesdiv.appendChild(choiceLabel);
		opt.appendChild(choicesdiv);

		
    }

	startCount();
   
}

loadQuestion();



// loadQues();




async function loadScore() {
	var user_id=localStorage.getItem("user_id");
	document.getElementById("demo").style.display = "none";
	payload={
		"user_id":user_id,
		"answer":jsonans
	}

	const scoreRes= await fetch('http://192.168.1.178:8080/game/webapi/user/submitanswers',
	{
		method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
		body: JSON.stringify(payload)
	});
	var scoredata=await scoreRes.json();

	const scorceget=scoredata.data.correct;
	console.log(scoredata.data.correct);
	document.getElementById("score").innerHTML=scorceget;

	if(scorceget != 0){

		const totalScore = document.getElementById("score")
		totalScore.textContent = `You scored ${scorceget} out of ${Questions.length}`
	}else{
		const totalScore = document.getElementById("score")
		totalScore.textContent = `You scored nthg out of please sit and study `
	}

}


function nextQuestion() {
	timer_on = 0;
	counter = 0;
	clearTimeout(timeout);
    console.log(currQuestion,"present");
	if (currQuestion < Questions.length - 1) {
		currQuestion++;
        // console.log(currQuestion,"after");
        // document.getElementById("opt").remove()
		
		loadQuestion();
	} else {
		document.getElementById("opt").remove()
		document.getElementById("ques").remove()
		document.getElementById("btn").remove()
		loadScore();
        // console.log(jsonans[0].qid);
	}
}

var jsonans=[];

async function checkAns() {
   // return;	
	const selectedAns =await parseInt(document.querySelector('input[name="answer"]').value);
    
//    let id= document.getElementById("ques").value;
let id=document.getElementById('ques').getAttribute('value');
    const json={
        "qid":id,
        "mid":selectedAns
    };
jsonans.push(json);
  

    // if(empty(selectedAns)){

    //     console.log("select otions to continue");

    // }else{
        nextQuestion();
   // }
    
}

var counter = 0;
var timeout;
var timer_on = 0;

function timedCount() {
	
	document.getElementById("demo").innerHTML = counter;
	
	counter++;
	timeout = setTimeout(timedCount, 1000);
	if(counter >=20){
		document.getElementById("demo").style.background = "red";
		document.getElementById("demo").style.color = "black";
	}

	if(counter==31){
	clearTimeout(timeout);
	timer_on = 0;
	counter = 0;
	document.getElementById("demo").style.background = "green";
	document.getElementById("demo").style.color = "white";
	checkAns();

	}
	
  }

  function startCount() {
	
	if (!timer_on) {
	  timer_on = 1;
	  timedCount();
	}
  }




