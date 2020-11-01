function starview(star1,star2,star3){
                
    var n = 1;
    var m = 1;
    var star = 0;
    for(;m <=3;m++)
    {
        if(m == 1)
        {
            star = star1;
        }
        else if(m == 2)
        {
            star = star2;
        }
        else
        {
            star = star3;
        }
        for(n=1;n<=star;n++)
        {
            var codeImg = document.getElementById(m +"star"+n);
            codeImg.src="image/star_fill.png";
        }
        for(;n<=5;n++)
        {
            var codeImg = document.getElementById(m + "star"+n);
            codeImg.src="image/star.png";
        }
    }
    
}