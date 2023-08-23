var queData=[];
async function validateForm(event) {
   
    event.preventDefault();
    // let mobile_no = document.forms["myfrom"]["mobile"].value;
    let mobile_no = document.getElementById("mobile_no").value;
    if (mobile_no.length == 10) {

        // const response = await fetch('/movies');

        var data = new FormData();
        data.append("mobile_no", mobile_no);

        let payload = {
            "mobile_no": mobile_no
        };

        const response = await fetch('http://192.168.1.178:8080/game/webapi/user/login',

            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            }

        );

        var res = await response.json();
        var get_user_id=res.data.user_id;
       
        localStorage.setItem("user_id",get_user_id);
        
        let rowID = document.getElementById('loginsucess');
        rowID.style.display='block';
    } else {
        //  return "Enter Valid Number ";
        document.getElementById("mobile_no").value = "Enter Valid Number ";
    }

    // this.getTerms();

    //    return "hii"
}

function closegame() {
    window.location.href = "index.html";
}

function navigateUrl(redirectpage) {
    window.location.href = "redirectpage";
}



async function getTerms(event) {
  
   event.preventDefault();

    //window.location.href="teramsandcondition.html";
    const taerms = await fetch('http://192.168.1.178:8080/game/webapi/user/gettandc',
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }
        
    );
    
    var taermsres = await taerms.json();

    localStorage.setItem("terms",taermsres.data[0].terms);
   
    window.location.href="teramsandcondition.html";
  return "excuted"; 
}


document.getElementById("condtion").innerHTML =  localStorage.getItem("terms");

async function taketest() {
    
    const question= await fetch('http://192.168.1.178:8080/game/webapi/user/questions',

    {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
       
    }
);
    
    var quesres = await question.json();
    localStorage.setItem("getQuestion",JSON.stringify(quesres));
    // queData=JSON.stringify(questionResponse.data);
   console.log(quesres); 

   //  console.log(typeof questionResponse);
  
    window.location.href="pagination.html";
     
}

//console.log(quesres.data);


