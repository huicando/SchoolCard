// pages/lists/lists.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (wx.getStorageSync("userInfo").cardId==null){
      wx.showToast({
        title: '当前未绑定校园卡',
        // icon: '',
        image: '/pages/logo/warn.png',
        duration: 3000,
        mask: true,
        complete: function(res) {
          wx.navigateTo({
            url: '/pages/locking/locking',
          })
        },
      })
    }else{
      this.countMoneyByCardId(wx.getStorageSync("userInfo").cardId);
      this.findByCardId(wx.getStorageSync("userInfo").cardId)
    }
   
  },
  countMoneyByCardId: function (e) {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/record/countMoneyByCardId',
      data: {
        cardId:e
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',
      success: function (e) {
        console.log(e);
        if (e.data.price < 10) {
          wx.showToast({
            title: '当前余额较低',
            image: '/pages/logo/warn.png',
            duration: 3000,
            mask: true,
          })
        }
        that.setData({
          card: e.data
        })
      }
    })
  },
  findByCardId:function(e){
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/record/findByCardId',
      data: {
        cardId: e
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',
      success: function (e) {
        console.log(e.data);
       that.setData({
         record:e.data
       })
        
      }
    })
  }
})