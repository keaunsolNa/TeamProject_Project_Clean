window.onload = function() {
	(function(obj){      
	                    obj.init();      
	                    $(obj.onLoad);
	                })
	                    ((function(){      
	                        var canvas = $("#canvas");      
	                        var div = canvas.parent("div");      
	
	                        var ctx = canvas[0].getContext("2d");      
	                        var drawble = false;            
	
	                        function canvasResize(){        
	                            canvas[0].height = div.height();        
	                            canvas[0].width = div.width();      
	                        }      
	
	                        // PC 
	                        function draw(e){        
	                            function getPosition(){          
	                                return {            
	                                    X: e.pageX - canvas[0].offsetLeft,             
	                                    Y: e.pageY - canvas[0].offsetTop          
	                                }        
	                            }        
	                            switch(e.type){          
	                                case "mousedown":{            
	                                    drawble = true;            
	                                    ctx.beginPath();            
	                                    ctx.moveTo(getPosition().X, 
	                                    getPosition().Y);          
	                                }     
	
	                                break;          
	                                
	                                case "mousemove":{            
	                                    if(drawble){              
	                                        ctx.lineTo(getPosition().X, 
	                                        getPosition().Y);              
	                                        ctx.stroke();            
	                                    }          
	                                }          
	                                
	                                break;          
	                                
	                                case "mouseup":          
	                                case "mouseout":{            
	                                    drawble = false;            
	                                    ctx.closePath();          
	                                }          break;        
	                            }      
	                        }       
	
	                        // mobile    
	                         function touchdraw(e){        
	                            function getPosition(){          
	                                return {            
	                                    X: e.changedTouches[0].pageX - canvas[0].offsetLeft,             
	                                    Y: e.changedTouches[0].pageY - canvas[0].offsetTop          
	                                }        
	                            }        
	                            
	                            switch(e.type){          
	                                case "touchstart":{            
	                                    drawble = true;            
	                                    ctx.beginPath();            
	                                    ctx.moveTo(getPosition().X, 
	                                    getPosition().Y);          
	                                }          
	                                break;          
	                                case "touchmove":{            
	                                    if(drawble){             
	                                        e.preventDefault();              
	                                        ctx.lineTo(getPosition().X, 
	                                        getPosition().Y);              
	                                        ctx.stroke();            
	                                    }          
	                                }          
	                                
	                                break;          
	                                
	                                case "touchend":          
	                                case "touchcancel":{            
	                                    drawble = false;            
	                                    ctx.closePath();          
	                                }          
	                                
	                                break;        
	                            }      
	                        }      
	                        return {init: function(){          
	                            $(window).on("resize", canvasResize);                    
	                            canvas.on("mousedown", draw);          
	                            canvas.on("mousemove", draw);          
	                            canvas.on("mouseup", draw);          
	                            canvas.on("mouseout", draw);  
	
	                            canvas.on("touchstart", touchdraw);          
	                            canvas.on("touchend", touchdraw);          
	                            canvas.on("touchcancel", touchdraw);          
	                            canvas.on("touchmove", touchdraw);          
	
	                            // save 버튼을 누르면 imageupload.php로 base64코드를 보내서 이미지로 변환합니다.          
	                            
	                            $("#save").on("click", function(){            
	                                // a 태그를 만들어서 다운로드를 만듭니다.            
	                                var link = document.createElement('a');             
	                                // base64데이터 링크 달기            
	                                link.href = canvas[0].toDataURL("image/png");             
	                                // 다운로드시 파일명 지정            
	                                link.download = "image.png";             
	                                // body에 추가            
	                                document.body.appendChild(link);             
	                                link.click();             
	                                document.body.removeChild(link);             
	                                // 다운로드용 a 태그는 다운로드가 끝나면 삭제합니다.            
	                                form.remove();          
	                            });        
	                            
	                            $("#reset").on("click", function(){
	
	                                ctx.clearRect(0, 0, 800, 600);
	                            })
	
	                        },        
	                        
	                        onLoad: function(){          
	                            canvasResize();        
	                            }}})());  
		
						
};						