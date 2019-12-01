// pages/adjunction/adjunction.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: null,
    picker: ['饭堂', '小卖部', '其他'],

  },
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
    })
  },
  formSubmit: function (e) {
    console.log(e.detail.value)
    this.addRecord(e.detail.value)
    
  },
  addRecord: function (e) {
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/record/addRecord',
      data: {
       cardId:wx.getStorageSync("userInfo").cardId,
       way:e.way?this.data.picker[e.way]:'其他',
       price:e.price,
       mark:e.mark
        
      },
      header: {
        // "Content-Type": "application/x-www-form-urlencoded"
        "Content-Type": 'application/json;charset=UTF-8'
      },
      method: 'post',
      success: function (e) {
        wx.showToast({
          title: '添加成功',
          // icon: '',
          // image: '',
          duration: 3000,
          mask: true,
          complete: function(res) {
            that.setData({
              message:null
            })
          },
        })
      }
    })
  }

})