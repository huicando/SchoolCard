// pages/recharge/recharge.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  formSubmit: function (e) {
    console.log(e.detail.value)
    var record=e.detail.value
    // this.addRecord(e.detail.value)
    
    if(!record.cardId || !record.password || !record.price){
    
      wx.showToast({
        title: '请填写完整信息',
        // icon: '',
        image: '/pages/logo/warn.png',
        duration: 2000,
        mask: true,

      })
    }else {
      this.checkCard(record);
    }

  },

  addRecord: function (e) {
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/record/addRecord',
      data: {
        cardId: wx.getStorageSync("userInfo").cardId,
        way:'充值',
        price: e.price,
        mark: e.mark

      },
      header: {
        // "Content-Type": "application/x-www-form-urlencoded"
        "Content-Type": 'application/json;charset=UTF-8'
      },
      method: 'post',
      success: function (e) {
        console.log(e.data)
        wx.showToast({
          title: '充值成功',
          duration: 3000,
          mask: true,
          complete: function (res) {
            that.setData({
              message: null
            })
          
          },
        })
      },

    })
  },
  checkCard: function (e) {
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/card/checkCard1',
      data: {
        cardId: e.cardId,
        password: e.password
      },
      header: {
        // "Content-Type": "application/x-www-form-urlencoded"
        "Content-Type": 'application/json;charset=UTF-8'
      },
      method: 'post',
      success: function (res) {
        if(res.data){
          that.addRecord(e);
        }else{
          wx.showToast({
            title: '账号或者密码错误',
            // icon: '',
            image: '/pages/logo/warn.png',
            duration: 1000,
            mask: true,
           
          })
        }
      }
    })
   }

  
})