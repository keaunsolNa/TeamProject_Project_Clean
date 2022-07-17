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
		
		                        return {init: function(){          
		                            $(window).on("resize", canvasResize);                    
		                            canvas.on("mousedown", draw);          
		                            canvas.on("mousemove", draw);          
		                            canvas.on("mouseup", draw);          
		                            canvas.on("mouseout", draw);  
		
		                            $("#save").on("click", function(){     
		                            	const canvas = document.getElementById('canvas');
		                            	const dataURL = canvas.toDataURL();
		                            	document.getElementById("signImg").src = dataURL;
		                            	console.log(dataURL)
		                            	document.getElementById("canvas").remove();
		                            	
		                            });        
		                            
		                            $("#reset").on("click", function(){
		
		                                ctx.clearRect(0, 0, 800, 600);
		                            })
		
		                        },        
		                        
		                        onLoad: function(){          
		                            canvasResize();        
		                            }}})());  
			
							
	};			