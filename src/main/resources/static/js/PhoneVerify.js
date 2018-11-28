/*
   实现短信验证按钮倒计时效果
   （Cookie js文件在Cookie.js中）
*/
  var countdown;

  function Verify(obj,cookieName){   //obj 为，实现倒计时的按钮  cookieName 为 设置的cookie的名称
  countdown=getCookieValue(cookieName);
  if(countdown>0){
  settime(obj,cookieName);
  }
  else{
  addCookie(cookieName,60,60);
  obj.disabled=true;
  settime(obj,cookieName);
  }
  }

  //开始倒计时

  function settime(obj,cookieName) {
      countdown=getCookieValue(cookieName);
      if (countdown == 0) {
          document.registerForm.VerifyButton.value =" 重新发送 ";
          document.registerForm.VerifyButton.disabled=false;
          return;
      } else {
          obj.value = " (" + countdown +")秒后重新发送";
          countdown--;
          editCookie(cookieName,countdown,countdown+1);
      }
      setTimeout(function() { settime(obj,cookieName) },1000) //每1000毫秒执行一次
  }
