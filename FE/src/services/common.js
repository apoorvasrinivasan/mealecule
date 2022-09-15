
export default {
  Alert:(msg)=> {
    let div = document.createElement('div');
    let alert = document.createElement('div');
    let heading = document.createElement('div');
    let button = document.createElement('button');
    div.classList.add('alertBox');
    div.setAttribute('aria-modal','true');
    alert.classList.add('alert');
    heading.classList = "ui header small teal".split();
    heading.innerHTML = "Alert";
    button.classList = 'ui primary right aligned button'.split();
    button.innerHTML = "OK";
    div.appendChild(alert);
    alert.appendChild(heading);
    alert.appendChild(document.createTextNode(msg));
    alert.appendChild(button);
    document.body.style.overflowY = 'hidden';
    document.body.appendChild(div);
    button.focus();
    button.addEventListener('click',function(){
      document.body.removeChild(div);
      document.body.style.overflowY = 'auto';
    });

 }
}