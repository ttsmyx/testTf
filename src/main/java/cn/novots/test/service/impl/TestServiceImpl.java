package cn.novots.test.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.novots.test.model.detail.DataResultVo;
import cn.novots.test.model.detail.PostVo;
import cn.novots.test.model.list.DataPost;
import cn.novots.test.model.list.DataResult;
import cn.novots.test.model.list.ItemsVo;
import cn.novots.test.model.list.NextInfo;
import cn.novots.test.model.list.PagingInfo;
import cn.novots.test.model.query.PageInfo;
import cn.novots.test.model.query.QueryInfo;
import cn.novots.test.model.query.Variable;
import cn.novots.test.model.queryDetail.PostMeteringOptions;
import cn.novots.test.model.queryDetail.QueryDetail;
import cn.novots.test.model.queryDetail.VariableDetail;
import cn.novots.test.service.TestService;
import cn.novots.test.util.HttpClientUtil;
import cn.novots.test.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ttscjr
 *
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

	public static List<DataPost> datalist = new ArrayList<>();

	public static List<PostVo> pdfList = new ArrayList<>();

	@Override
	public void createPdfList() {
		TestServiceImpl test = new TestServiceImpl();
		try {
			test.getList("0", "20", 20);
		} catch (Exception e) {
			log.info("停止抓取", e);
		}
		Collections.sort(datalist, new Comparator<DataPost>() {
			@Override
			public int compare(DataPost o1, DataPost o2) {
				return Long.compare(o2.getClapCount(), o1.getClapCount()); // 按点赞进行排序
			}
		});
		// 详情
		for (int i = 0; i < 10; i++) {
			DataPost dataPost = datalist.get(i);
			test.getDetail(dataPost.getId(),dataPost.getTitle());
		}
		log.info("pdfList.size ={}", pdfList.size());
	}

	public PostVo getDetail(String postId,String titleName) {
		String url = "https://medium.com/_/graphql";

		String query = "query PostViewerEdgeContentQuery($postId: ID!, $postMeteringOptions: PostMeteringOptions) {\n  post(id: $postId) {\n    ... on Post {\n      id\n      viewerEdge {\n        id\n        fullContent(postMeteringOptions: $postMeteringOptions) {\n          isLockedPreviewOnly\n          validatedShareKey\n          bodyModel {\n            ...PostBody_bodyModel\n            __typename\n          }\n          ...FriendLinkMeter_postContent\n          __typename\n        }\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n\nfragment PostBody_bodyModel on RichText {\n  sections {\n    name\n    startIndex\n    textLayout\n    imageLayout\n    backgroundImage {\n      id\n      originalHeight\n      originalWidth\n      __typename\n    }\n    videoLayout\n    backgroundVideo {\n      videoId\n      originalHeight\n      originalWidth\n      previewImageId\n      __typename\n    }\n    __typename\n  }\n  paragraphs {\n    id\n    ...PostBodySection_paragraph\n    __typename\n  }\n  ...normalizedBodyModel_richText\n  __typename\n}\n\nfragment PostBodySection_paragraph on Paragraph {\n  name\n  ...PostBodyParagraph_paragraph\n  __typename\n  id\n}\n\nfragment PostBodyParagraph_paragraph on Paragraph {\n  name\n  type\n  ...ImageParagraph_paragraph\n  ...TextParagraph_paragraph\n  ...IframeParagraph_paragraph\n  ...MixtapeParagraph_paragraph\n  ...CodeBlockParagraph_paragraph\n  __typename\n  id\n}\n\nfragment ImageParagraph_paragraph on Paragraph {\n  href\n  layout\n  metadata {\n    id\n    originalHeight\n    originalWidth\n    focusPercentX\n    focusPercentY\n    alt\n    __typename\n  }\n  ...Markups_paragraph\n  ...ParagraphRefsMapContext_paragraph\n  ...PostAnnotationsMarker_paragraph\n  __typename\n  id\n}\n\nfragment Markups_paragraph on Paragraph {\n  name\n  text\n  hasDropCap\n  dropCapImage {\n    ...MarkupNode_data_dropCapImage\n    __typename\n    id\n  }\n  markups {\n    ...Markups_markup\n    __typename\n  }\n  __typename\n  id\n}\n\nfragment MarkupNode_data_dropCapImage on ImageMetadata {\n  ...DropCap_image\n  __typename\n  id\n}\n\nfragment DropCap_image on ImageMetadata {\n  id\n  originalHeight\n  originalWidth\n  __typename\n}\n\nfragment Markups_markup on Markup {\n  type\n  start\n  end\n  href\n  anchorType\n  userId\n  linkMetadata {\n    httpStatus\n    __typename\n  }\n  __typename\n}\n\nfragment ParagraphRefsMapContext_paragraph on Paragraph {\n  id\n  name\n  text\n  __typename\n}\n\nfragment PostAnnotationsMarker_paragraph on Paragraph {\n  ...PostViewNoteCard_paragraph\n  __typename\n  id\n}\n\nfragment PostViewNoteCard_paragraph on Paragraph {\n  name\n  __typename\n  id\n}\n\nfragment TextParagraph_paragraph on Paragraph {\n  type\n  hasDropCap\n  codeBlockMetadata {\n    mode\n    lang\n    __typename\n  }\n  ...Markups_paragraph\n  ...ParagraphRefsMapContext_paragraph\n  __typename\n  id\n}\n\nfragment IframeParagraph_paragraph on Paragraph {\n  type\n  iframe {\n    mediaResource {\n      id\n      iframeSrc\n      iframeHeight\n      iframeWidth\n      title\n      __typename\n    }\n    __typename\n  }\n  layout\n  ...Markups_paragraph\n  __typename\n  id\n}\n\nfragment MixtapeParagraph_paragraph on Paragraph {\n  type\n  mixtapeMetadata {\n    href\n    mediaResource {\n      mediumCatalog {\n        id\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n  ...GenericMixtapeParagraph_paragraph\n  __typename\n  id\n}\n\nfragment GenericMixtapeParagraph_paragraph on Paragraph {\n  text\n  mixtapeMetadata {\n    href\n    thumbnailImageId\n    __typename\n  }\n  markups {\n    start\n    end\n    type\n    href\n    __typename\n  }\n  __typename\n  id\n}\n\nfragment CodeBlockParagraph_paragraph on Paragraph {\n  codeBlockMetadata {\n    lang\n    mode\n    __typename\n  }\n  __typename\n  id\n}\n\nfragment normalizedBodyModel_richText on RichText {\n  paragraphs {\n    ...normalizedBodyModel_richText_paragraphs\n    __typename\n  }\n  sections {\n    startIndex\n    ...getSectionEndIndex_section\n    __typename\n  }\n  ...getParagraphStyles_richText\n  ...getParagraphSpaces_richText\n  __typename\n}\n\nfragment normalizedBodyModel_richText_paragraphs on Paragraph {\n  markups {\n    ...normalizedBodyModel_richText_paragraphs_markups\n    __typename\n  }\n  codeBlockMetadata {\n    lang\n    mode\n    __typename\n  }\n  ...getParagraphHighlights_paragraph\n  ...getParagraphPrivateNotes_paragraph\n  __typename\n  id\n}\n\nfragment normalizedBodyModel_richText_paragraphs_markups on Markup {\n  type\n  __typename\n}\n\nfragment getParagraphHighlights_paragraph on Paragraph {\n  name\n  __typename\n  id\n}\n\nfragment getParagraphPrivateNotes_paragraph on Paragraph {\n  name\n  __typename\n  id\n}\n\nfragment getSectionEndIndex_section on Section {\n  startIndex\n  __typename\n}\n\nfragment getParagraphStyles_richText on RichText {\n  paragraphs {\n    text\n    type\n    __typename\n  }\n  sections {\n    ...getSectionEndIndex_section\n    __typename\n  }\n  __typename\n}\n\nfragment getParagraphSpaces_richText on RichText {\n  paragraphs {\n    layout\n    metadata {\n      originalHeight\n      originalWidth\n      id\n      __typename\n    }\n    type\n    ...paragraphExtendsImageGrid_paragraph\n    __typename\n  }\n  ...getSeriesParagraphTopSpacings_richText\n  ...getPostParagraphTopSpacings_richText\n  __typename\n}\n\nfragment paragraphExtendsImageGrid_paragraph on Paragraph {\n  layout\n  type\n  __typename\n  id\n}\n\nfragment getSeriesParagraphTopSpacings_richText on RichText {\n  paragraphs {\n    id\n    __typename\n  }\n  sections {\n    ...getSectionEndIndex_section\n    __typename\n  }\n  __typename\n}\n\nfragment getPostParagraphTopSpacings_richText on RichText {\n  paragraphs {\n    type\n    layout\n    text\n    codeBlockMetadata {\n      lang\n      mode\n      __typename\n    }\n    __typename\n  }\n  sections {\n    ...getSectionEndIndex_section\n    __typename\n  }\n  __typename\n}\n\nfragment FriendLinkMeter_postContent on PostContent {\n  validatedShareKey\n  shareKeyCreator {\n    ...FriendLinkSharer_user\n    __typename\n    id\n  }\n  __typename\n}\n\nfragment FriendLinkSharer_user on User {\n  id\n  name\n  ...userUrl_user\n  __typename\n}\n\nfragment userUrl_user on User {\n  __typename\n  id\n  customDomainState {\n    live {\n      domain\n      __typename\n    }\n    __typename\n  }\n  hasSubdomain\n  username\n}\n";
		QueryDetail detail = new QueryDetail();
		detail.setOperationName("PostViewerEdgeContentQuery");
		detail.setQuery(query);

		PostMeteringOptions postMeteringOptions = new PostMeteringOptions();
		postMeteringOptions.setReferrer("https://medium.com/");

		VariableDetail variable = new VariableDetail();
		variable.setPostId(postId);
		variable.setPostMeteringOptions(postMeteringOptions);

		detail.setVariables(variable);

		List<QueryDetail> list = new ArrayList<>();
		list.add(detail);
		String json = JacksonUtil.toJSONString(list);

		String value = HttpClientUtil.doPostWithParam(url, json, null);

		List<DataResultVo> volist = JacksonUtil.toJavaObjectList(value, DataResultVo.class);

		if (volist != null && volist.size() > 0) {
			PostVo postVo = volist.get(0).getData().getPost();
			postVo.setTitleName(titleName);
			pdfList.add(postVo);
			return postVo;
		}
		return null;
	}

	private void getList(String from, String to, int limit) {
		List<QueryInfo> list = new ArrayList<>();

		String url = "https://medium.com/_/graphql";
		String text = "query TagRecommendedFeedQuery($tagSlug: String!, $paging: PagingOptions) {\n  tagFromSlug(tagSlug: $tagSlug) {\n    id\n    viewerEdge {\n      id\n      recommendedPostsFeed(paging: $paging) {\n        items {\n          feedId\n          reason\n          moduleSourceEncoding\n          post {\n            ...StreamPostPreview_post\n            __typename\n          }\n          __typename\n        }\n        pagingInfo {\n          next {\n            from\n            limit\n            source\n            to\n            __typename\n          }\n          __typename\n        }\n        __typename\n      }\n      __typename\n    }\n    __typename\n  }\n}\n\nfragment StreamPostPreview_post on Post {\n  id\n  ...StreamPostPreviewContent_post\n    __typename\n}\n\nfragment StreamPostPreviewContent_post on Post {\n  id\n  title\n        ...PostPreviewFooter_post\n     __typename\n}\n\nfragment PostPreviewFooter_post on Post {\n    ...PostPreviewFooterMenu_post\n  __typename\n  id\n}\n\nfragment MultiVoteCount_post on Post {\n  id\n  __typename\n}\n\nfragment PostPreviewFooterMenu_post on Post {\n    collection {\n    __typename\n    id\n  }\n    ...ExpandablePostCardOverflowButton_post\n  __typename\n  id\n}\n\nfragment ExpandablePostCardOverflowButton_post on Post {\n    ...ExpandablePostCardReaderButton_post\n  __typename\n  id\n}\n\nfragment ExpandablePostCardReaderButton_post on Post {\n  id\n  collection {\n    id\n    __typename\n  }\n  mediumUrl\n  clapCount\n  ...ClapMutation_post\n  __typename\n}\n\nfragment ClapMutation_post on Post {\n  __typename\n  id\n  clapCount\n  ...MultiVoteCount_post\n}\n\n";

		PageInfo page = new PageInfo();
		page.setFrom(from);
		page.setLimit(limit);
		page.setTo(to);

		Variable variables = new Variable();
		variables.setPaging(page);
		variables.setTagSlug("software-engineering");

		QueryInfo query = new QueryInfo();
		query.setOperationName("TagRecommendedFeedQuery");
		query.setQuery(text);
		query.setVariables(variables);
		list.add(query);
		String json = JacksonUtil.toJSONString(list);

		String value = HttpClientUtil.doPostWithParam(url, json, null);

		List<DataResult> resultList = JacksonUtil.toJavaObjectList(value, DataResult.class);
		if (resultList != null && resultList.size() > 0) {
			List<ItemsVo> itemList = resultList.get(0).getData().getTagFromSlug().getViewerEdge()
					.getRecommendedPostsFeed().getItems();
			for (ItemsVo item : itemList) {
				datalist.add(item.getPost());
			}
			log.info("datalist.size ={}", datalist.size());
			PagingInfo pageInfo = resultList.get(0).getData().getTagFromSlug().getViewerEdge().getRecommendedPostsFeed()
					.getPagingInfo();
			if (pageInfo != null) {
				NextInfo nextInfo = pageInfo.getNext();
				if (nextInfo != null && nextInfo.getLimit() > 0) {
					this.getList(nextInfo.getFrom(), nextInfo.getTo(), nextInfo.getLimit());
				}
			}
		}
		return;
	}

}
