function bme280()
{
    if(android.STATE_String()=="SUCCESS")
    {
        android.Init();
        android.readData();
        $("#temperture .value").text(android.Temperature().toFixed(2));
        $("#humid .value").text(android.Humidity().toFixed(2));
        $("#pressure .value").text((android.Pressure()/100).toFixed(2));
    }
    else
    {
        $("#temperture .value").text("");
        $("#humid .value").text("");
        $("#pressure .value").text("");
    }

    $("#refresh .value").text(new Date());

    if(android.STATE_String()=="SUCCESS")
    {
        $("#status .value").text("接続完了");
    }
    else
    {
        $("#status .value").text("AN-BME280-IGを接続して下さい");
    }



    //$("#temperture .value").text( document.getElementById("VERSION").innerText = android.VERSION();
    console.log("triggerd");
}
$(function(){
    setInterval(bme280, 500);
});
