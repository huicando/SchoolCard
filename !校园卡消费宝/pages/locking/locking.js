// pages/locking/locking.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  formSubmit:function(e){
    console.log(e.detail.value)
    var card=e.detail.value
    if(!card.cardId || !card.password){
      wx.showToast({
        title: '请填写完整信息',
        // icon: '',
        image: '/pages/logo/warn.png',
        duration: 2000,
        mask: true,

      })
    }else{
      this.checkCard(e.detail.value)
    }
   

  },
  checkCard: function (e) {
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/card/checkCard',
      data: {
        openid: wx.getStorageSync("openid").openid,
        cardId: e.cardId,
        password: e.password
      },
      header: {
        // "Content-Type": "application/x-www-form-urlencoded"
        "Content-Type": 'application/json;charset=UTF-8'
      },
      method: 'post',
      success: function (res) {
        console.log(res.data)
        if(res.data){
          wx.showToast({
            title: '绑定成功',
            // icon: '',
            // image: '',
            duration: 3000,
            mask: true,
            complete: function(res) {
              var pages = getCurrentPages();
              var beforePage = pages[pages.length - 2];
              beforePage.onLoad();
              wx.navigateBack({
                delta: 1,
              })
            },
          })
          var userInfo = wx.getStorageSync("userInfo");
          userInfo.cardId =e.cardId
          wx.setStorageSync("userInfo", userInfo)
        }else{
          wx.showToast({
            title: '输入错误',
            // icon: '',
            image: '/pages/logo/warn.png',
            duration: 2000,
            mask: true,
          })
        }
      },
      complete:function(e){
        that.setData({
          message:null
        })
      }

    })
  }
  

  
})