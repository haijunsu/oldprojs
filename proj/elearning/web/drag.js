var dragObj
drag = 0;
move = 0;
function init() 
{
    window.document.onmousemove = mouseMove
    window.document.onmousedown = mouseDown
    window.document.onmouseup = mouseUp
    window.document.ondragstart = mouseStop
}
function mouseDown() 
{
    if (drag) 
    {
        clickleft = window.event.x - parseInt(dragObj.style.left)
        clicktop = window.event.y - parseInt(dragObj.style.top)
        dragObj.style.zIndex += 1
        move = 1
    }
}
function mouseStop() 
{
    window.event.returnValue = false
}
function mouseMove() 
{
    if (move) 
    {
        dragObj.style.left = window.event.x - clickleft
        dragObj.style.top = window.event.y - clicktop
    }
}
function mouseUp() 
{
    move = 0
}
function click() 
{
	if (event.button==2) 
	{
		alert('对不起,禁止使用此功能.')
	}
}
document.onmousedown=click