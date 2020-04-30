package io.scal.commandbasedarchitecture.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Model class that store: pageData, refresh state and next page loading state
 */
@Parcelize
data class PaginationState<
        UIBaseItem,
        UIDataItem : UIBaseItem,
        Data : PageData<UIDataItem>>
    (
    val pageData: Data? = null,
    val refreshStatus: @RawValue UIBaseItem? = null,
    val nextPageLoadingStatus: @RawValue UIBaseItem? = null
) : Parcelable

/**
 * Combines page data items with next page loading state item. Useful for RecyclerView adapters.
 */
val <UIBaseItem, UIDataItem : UIBaseItem, Data : PageData<UIDataItem>> PaginationState<UIBaseItem, UIDataItem, Data>.dataAndNextPageLoadingStatus: List<UIBaseItem>?
    get() = combineUIItemAndNextPage(
        pageData?.itemsList,
        nextPageLoadingStatus
    )

/**
 * Combines page data items with next page loading state item. Useful for RecyclerView adapters.
 */
fun <UIBaseItem, UIDataItem : UIBaseItem> combineUIItemAndNextPage(
    data: List<UIDataItem>?,
    nextPageLoadingStatus: UIBaseItem?
): List<UIBaseItem>? =
    if (null == nextPageLoadingStatus) data
    else (data ?: emptyList()).plus(nextPageLoadingStatus)