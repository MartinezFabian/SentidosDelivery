package org.example.sentidosdelivery.adapter

import org.example.sentidosdelivery.model.ItemMenu

interface MenuAdapterListener {
    fun onBuyMenuClicked(itemMenu: ItemMenu)
}